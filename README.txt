# Objectives

To implement a Bounded Priority Queue ADT (Abstract Data Type) where each element inserted into the queue is made up of a key-value pair: the key determines the element's priority within the queue.
The first-in/first-out rule is not enforced in this data structure as the elements with a higher priority will be de-queued first (FIFO). If, however, two or more elements are equally prioritized, the FIFO policy is thus observed.

# Programming Language & Compiler

Language: Java (AspectJ)

Compiler: ajc

# Tools

### AspectJ
AspectJ is an aspect-oriented programming (AOP) extension created at PARC for the Java programming language. .

***Installation & Usage***
- **Eclipse** <br>
AspectJ can be acquired within Eclipse using AJDT (AspectJ Development Tools). <br>
1. Help > install new software
2. add
    - Name : AJDT
    - Location : http://download.eclipse.org/tools/ajdt/410/dev/update (Eclipse V 4.10)
    - Location : http://download.eclipse.org/tools/ajdt/48/dev/update (Eclipse V 4.8)
    - Location : http://download.eclipse.org/tools/ajdt/47/dev/update (Eclipse V 4.7)
3. Select all that apply > Next > Next
4. Accept terms and condition > Finish
5. Restart Eclipse
6. File > New > AspectJ Project

<br>

### adbc - Design by Contract for AspectJ

![adbc logo](https://raw.github.com/timmolderez/adbc/master/doc/adbc.png)

Adbc is a small library that adds support for [design by contract](http://en.wikipedia.org/wiki/Design_by_contract) to the [AspectJ](http://eclipse.org/aspectj/) programming language.
<sub>Released under the [BSD 3-Clause license](http://opensource.org/licenses/BSD-3-Clause).</sub>

- Add the library to your AspectJ project and contracts will automatically be enforced at runtime. Whenever a contract is broken, an exception is thrown which also indicates who is to blame.
- Contracts are written as JavaScript expressions within Java annotations. (The library uses the JSR 223 API, so you can easily configure which scripting engine is used to evaluate the contracts.)
- Contract enforcement is guided by [behavioural subtyping](http://en.wikipedia.org/wiki/Liskov_substitution_principle) and the [advice substitution principle](http://dl.acm.org/citation.cfm?id=2162015), which allows for modular reasoning. That is, you can reason about a method call just by looking at the contracts of the method body in the static type, even in the presence of advice. In a nutshell, to ensure that an advice complies with the advice substitution principle, its contracts should not interfere with those of the methods being advised. If an advice cannot avoid breaking the principle, modular reasoning can be0 restored by explicitly mentioning that advice's name in an `@advisedBy` annotation at each method it advises. (See the [documentation](https://raw.github.com/timmolderez/adbc/master/doc/README.pdf) for more information.)

### [Download adbc](http://timmolderez.be/builds/adbc/)
https://github.com/timmolderez/adbc


***Requirements***

- Java 6 (or later)
- AspectJ (versions 1.6.12 and 1.7.2)

***Installation & Usage***

- **Eclipse** <br>
When using Eclipse+AJDT, just add `adbc.jar` to your project's InPath and you can start writing contracts (using the annotations in `be.ac.ua.ansymo.adbc.annotations`). Contract enforcement is automatically enabled, and can be disabled if needed via the `AdbcConfig` class.
For more information, be sure to have a look at adbc's [documentation](https://raw.github.com/timmolderez/adbc/master/doc/README.pdf).

1. Download adbc JAR (see link above)
2. Right-click on AspectJ project folder > Properties
3. AspectJ Build > InPath
4. Add External JARs > Select previously installed adbc JAR

Now you can use adbc by simply importing ***be.ac.ua.ansymo.adbc.annotations.***
