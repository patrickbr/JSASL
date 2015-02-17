var sasler;
var printer;

saslInit = function() {
  sasler = new sasl.Sasl();
  printer = new sasl.Printer();
  // default code
  load("primesieve");
};

function eval(source, target, cancelbut, evalbut) {
  cancel();
  switchButtons(true, cancelbut, evalbut);
  var val = document.getElementById(source).value;
  document.getElementById(target).value = '';

  window.setTimeout(function() {
    try {
      var res = sasler.compileAndReduce(val, function(error) {
        showMsg(error);
        switchButtons(false);
      });

      if (res) {
        printer.print(res, 1,
          function(val) {
            // on val print
            document.getElementById(target).value += val
          },
          function(error) {
            // on error
            showMsg(error);
            switchButtons(false);
          },
          function() {
            // on finish
            switchButtons(false);
          }
        );
      } else {
        // on no ros
        switchButtons(false);
      }
    } catch(e) {
      // on exception
      showMsg(e);
      switchButtons(false);
    }
  }, 100);
}

function switchButtons(started, cancelbut, evalbut) {
  if (started) {
    document.getElementById(cancelbut).style.display = 'block';
    document.getElementById(evalbut).innerHTML = '<i class="fa fa-circle-o-notch fa-spin"></i>';
    document.getElementById(evalbut).className = 'evalbutton running';
  } else {
    var buttons = document.getElementsByClassName('cancelbutton');
    for (var i = 0; i<buttons.length; i++) {
      var button = buttons[i];
      button.style.display = 'none';
    }
    buttons = document.getElementsByClassName('evalbutton');
    for (i = 0; i<buttons.length; i++) {
      var button = buttons[i];
      button.innerHTML = '<i class="fa fa-play"></i>';
      button.className = 'evalbutton';
    }
  }
}

function cancel() {
  printer.cancel();
  switchButtons(false);
}

function showMsg(msg) {
  document.getElementById('msgInner').innerHTML = msg;
  document.getElementById('msg').style.display = 'block';
}

function hideMsg() {
  document.getElementById('msg').style.display = 'none';
}

function load(what) {
  var str;
  switch(what) {
    case 'associativity':
      str = '1+1*2 = 3';
      break;
    case 'primesieve':
      str = 'def take n l = if n=0 or l=nil then nil\nelse x:take (n-1) xs where x = hd l;\nxs = tl l\ndef mod x y = (x - (x/y)*y)\ndef primes = sieve (naturals 2)\n\ndef sieve input = (hd input) : (sieve (removeFromList (tl input) (hd input)))\n\ndef removeFromList list ele =if (mod (hd list) ele) = 0\nthen (removeFromList (tl list) ele)\n                        else (hd list) : (removeFromList (tl list) ele)\n\ndef naturals x = x : (naturals (x+1))\n\n.\n\ntake 20 primes';
      break;
    case 'funcInLine':
      str = 'def at n l = if n=0 then hd l\nelse at (n-1) (tl l)	\ndef plus x y = x+y	\ndef minus x y = x-y\n.\nat 0 a (at 1 a 4 1) 2 where a = [plus,minus]'
      break;
    case 'dropTake':
      str = 'def drop n l = if n<=0 then l		\n	else if l=nil then nil				\n	else drop (n-1) (tl l)	\ndef take n l = if n=0 or l=nil then nil			\n	else x:take (n-1) xs where x = hd l;	\n				   xs = tl l\n\n  def append l1 l2 = if l1=nil then l2			\n	else x:(append xs l2) where x = hd l1;		\n					xs = tl l1				   \n\ndef list = [1,2,3,4,5,6,7,8,9,10]\n.\n\nappend (take 4 list) (drop 4 list)';
      break;
    case 'append':
      str = 'def append l1 l2 = if l1=nil then l2	\n	else x:(append xs l2) where x = hd l1;		\n					xs = tl l1			\n.					\nappend [1,2] [3,4] ';
      break;
    case 'reverse':
      str = 'def reverse l = if l=nil then nil		\n  else append (reverse (tl l)) ((hd l):nil)\ndef append l1 l2 = if l1=nil then l2		\n	else x:(append xs l2) where x = hd l1;\n\n					xs = tl l1					\n.	\nreverse [1,2,3]';
      break;
    case 'sort':
      str = 'def sort p l = if l=nil then nil			\n	else insert p (hd l) (sort p (tl l))	\n	where			\n	insert pp e ll = if ll=nil then (e:nil)		\n		else	\n		if pp e (hd ll) then (e:ll)		\n		else					\n		((hd ll):(insert pp e (tl ll)))			\n.		\n\nsort p [2,6,5,4,453,8,565,1337,9,1000,-44] where p x y = x<y';
      break;
    case 'naturals':
      str = 'def take n l = if n=0 or l=nil then nil\nelse x:take (n-1) xs where x = hd l;\nxs = tl l\n\ndef naturals x = x : (naturals (x+1))\n\n.\n\ntake 500 (naturals start) where start = 1';
      break;
    case 'scopes':
      str = 'def a x = b where b=c where c=d where d=e where e=x where x=y;y=23\ndef d=22\n.\n\n[a + (a where a=1) where a=c;c=d, ((a where a x=(b where b=x+(c where c=x))) 11)+1]';
      break;
    case 'faculty':
      str = 'def fac x = if x>1 then ((fac(x-1))*x) else 1\n.\nfac 12';
      break;
    case 'hanoi':
      str = 'def hanoi x = if x>1 then (1+(hanoi(x-1))*2) else 1\n.\nhanoi 7';
      break;
    case 'mccarthy':
      str = 'def mccarthy n = if n>100 then n-10 else mccarthy(mccarthy(n+11))\n.\nmccarthy 53';
      break;
    case 'ackermann':
      str = 'def ackermann m n = if m > 0 and n = 0 then (ackermann (m-1) 1) else if m > 0 and n > 0 then (ackermann (m-1) (ackermann m (n-1))) else n+1\n.\nackermann 3 3'
      break;
    case 'listat':
      str = 'def at l p = if p = 0 then hd l else (at (tl l) (p-1))\n.\nat ["a", "b", "c", "d", "e", "f"] 4';
      break;
    case 'stringimplode':
      str = 'def strimp_h strl tmp = if strl = nil then tmp else strimp_h (tl strl) (tmp + (hd strl))\ndef strimp strl = strimp_h strl ""\n.\nstrimp ["a", "b", "c"]';
      break;
    case 'fold':
      str = 'def fold m z l = if l=nil then z\n  else m x (fold m z xs) where x = hd l;	xs = tl l\ndef plus a b = a + b\n.\nfold plus 0 [1,2,3,4,5]';
      break;
    case 'filter':
      str = 'def take n l = if n=0 or l=nil then nil\nelse x:take (n-1) xs where x = hd l;\nxs = tl l\ndef naturals n = n : (naturals (n+1))\ndef filter p l = if l=nil then nil\n	else (if p x then x:(filter p xs)	\n	else filter p xs) where x = hd l;	\n			            xs = tl l	\ndef mod x y = (x - (x/y)*y)\ndef evens n = filter p (naturals n) where p a = mod a 2 = 0\n.\ntake 100 (evens 1)';
      break;
    case 'map':
      str = 'def map f l = if l=nil then nil\n  else (f x):(map f xs) where x = hd l;\n				  xs = tl l\ndef naturals x = x : (naturals (x+1))\ndef mod x y = (x - (x/y)*y)\n.\nmap f (naturals 0) where f n = mod n 2 \n';
      break;
    case 'fibonacci':
      str = 'def fibonacci n = if n < 3 then 1 else (fibonacci (n-1)) + (fibonacci (n-2))\ndef fibonaccis n = fibonacci n : fibonaccis(n+1)\ndef take n l = if n=0 or l=nil then nil	\n	else x:take (n-1) xs where x = hd l;		\n				   xs = tl l	\n.\ntake 15 (fibonaccis 1)';
      break;
    case 'collatz':
      str = 'def mod x y = (x - (x/y)*y)\ndef collatz n = n: (if c = 1 then nil else (collatz c)) where c = if mod n 2 = 0 then n/2 else 3*n + 1\n.\ncollatz 100000';
      break;

  }
  var texta = document.getElementById('texta');
  if (texta) texta.value = str;
}
