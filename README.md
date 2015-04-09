# JSASL

JSASL (see [example here](http://patrickbrosi.de/jsasl)) is the result of my ongoing efforts to compile the [saslcompiler](https://github.com/patrickbr/saslcompiler)'s Java code to a JavaScript library. The code is generated using [GWT](http://www.gwtproject.org/)'s JavaScript compiler and the [Closure compiler](https://developers.google.com/closure/compiler/). The classes are exported to a native JS environment by the [GWTExporter](http://code.google.com/p/gwt-exporter/).

## Build
* Download and install the [latest version of GWT](http://www.gwtproject.org/download.html).
* Copy `build.properties.example` to `build.proporties` and adjust to path to the GWT SDK
* Build `saslcompiler.min.js` by
```
$ ant build
```

## Usage

There is a usage example included in `/example`. This example is also available here: http://patrickbrosi.de/jsasl/. A language guide can be found [here](http://patrickbrosi.de/jsasl/manual.html).
To use the compiler in your project, just include `saslcompiler.min.js` into your project. You can use the compiler and the reduction machine like this:

```
var sasler;
var printer;

saslInit = function() {
  sasler = new sasl.Sasl();
  printer = new sasl.Printer();

  var res = sasler.compileAndReduce('def a x = x*x . a 2');
  // now print the result
  var res;
  printer.print(res, 10,
    function(val) {
      res += val
    }
  };
);
```
Important: you have to define ```saslInit()```, as it is called as soon as the SASL classes are available.

## Known issues
* On-the-fly reduction machine error messages are cryptic, to say the least
* The reduction machine makes heavy use of recursion and is not executed asynchronously. Lists, however, are evaluted asynchronously and should not freeze the browser.
* Firefox seems to have problems with recursion-heavy calculations
