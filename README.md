# Replication

This repository serves as an artifact for the paper *Practical Range Refinement Types with Inference* (SEFM 2026).


## Directory structure

```text
/
|_ README.md .................................... [CURRENT FILE] Contains the replication instructions, please start from here
|_ ranger-image.tar ............................................ Docker image (available in the Figshare repository only)
|_ examples/
    |_ Dockerfile .................................................. The Dockerfile that we used to generate the image
    |_ java-checker-framework/ ................................. Checker Framework version of our examples
    |   |_ pom.xml ............................................. Maven configuration file
    |   |_ src/main/java/org/example/
    |       |_ arraymap/ ....................................... Every package corresponds to one example
    |       |   |_ ArrayMap.java
    |       |   |_ ArrayUtils.java
    |       |_ datetime/
    |       |   |_ ...
    |       |_ ...
    |_ licorne/ ................................................ Licorne/Ranger version of our examples
    |   |_ arraymap/ ........................................... Every subfolder corresponds to one example
    |   |   |_ arrays/Array.lic ................................ Some examples are split into packages
    |   |   |_ maps/
    |   |   |   |_ ArrayMap.lic
    |   |   |   |_ Map.lic
    |   |   |_ general_aliases.lic
    |   |   |_ OrderedCollections.lic
    |   |_ datetime/
    |   |   |_ ...
    |   |_ ...
    |_ liquid-java/ ............................................ LiquidJava version of our examples
    |   |_ src/main/java/
    |   |   |_ datetime ........................................ Every package corresponds to one example
    |   |   |   |_ ...
    |   |   |_ ...
    |_ scala/ .................................................. Scala version of our examples
    |   |_ build.sbt ........................................... SBT (Scala Build Tool) configuration file
    |   |_ src/main/scala/
    |   |   |_ arraymap/ ....................................... Every package corresponds to one example
    |   |   |   |_ ArrayMap.scala
    |   |   |_ ...
    |_ scripts/ ................................................ Automation scripts
    |   |_ comparison_script.py ................................ Script that collects the data in the formal comments and builds the results table
    |   |_ timing_script.py .................................... Script that runs and times the Ranger and Scala type-checkers
    |   |_ formal_comments_explanation.txt ..................... Description of the system of formal comments that we use
```


## Links to referenced tools
- Licorne: https://github.com/ValentinAebi/licorne-lang/ (Ranger is implemented in Licorne's type system)
- Java Checker Framework: https://checkerframework.org/
- LiquidJava: https://liquid-java.github.io/


## Sources of the examples

Examples `ic4`, `ic5`, `ic7`, and `ic9` are taken from the [manual of the Checker Framework](https://checkerframework.org/manual/) (sections 11.4, 11.5, 11.7, and 11.9, respectively). Examples `lj1` and `lj2` are taken from the [examples repository of LiquidJava](https://github.com/liquid-java/liquidjava-examples), `lj3` from the [Open VSX page of the LiquidJava VS Code extension](https://open-vsx.org/extension/AlcidesFonseca/liquid-java), and `lj4` from the [test suite of LiquidJava](https://github.com/liquid-java/liquidjava/tree/main/liquidjava-example/src/main/java/testSuite). Other examples were made by us.


## Reproducibility claims

We claim the *available* and *functional* badges, as well as the following functional outcomes.

F1 - Our [timing script](./examples/scripts/timing_script.py), which typechecks all examples in Ranger and Scala, shows that Ranger can check programs in a practical amount of time, while clearly and precisely reporting errors. This supports our claims in the last paragraph of the evaluation section (section 5) of the paper.

F2 - Ranger code examples, together with our [comparison script](./examples/scripts/comparison_script.py) that analyzes the formal comments we wrote next to code units, show that Ranger can typecheck all of our 14 examples without annotations besides the ones in method signatures and with a single use of the hybrid cast operator. This reproduces the results displayed in table 2 in the paper. We additionally provide a [description of our system of formal comments](./examples/scripts/formal_comments_explanation.txt).


## Steps to reproduce

1. Load the Docker image:
```sh
docker load -i ranger-image.tar
```
Alternatively, you can pull the image from DockerHub:
```
docker pull aebiv/ranger-image
```
Or build it from the [Dockerfile](./examples/Dockerfile):
```
docker build -t "aebiv/ranger-image" .
```


2. Run the image:
```sh
docker run -it aebiv/ranger-image
```

**Note**: This image was built on an x64-based machine. We tested on a Windows 11 and an Ubuntu 24.04 LTS Linux machines. You should be able to run it also on a Mac with the `--platform linux/amd64` emulation option, but performance may worsen. Alternatively, consider locally building the image from the Dockerfile (see instructions above).

You may want to perform the following sanity checks:
- `javac -version` should output `javac 25.0.3`
- `scalac -version` should output `Scala compiler version 3.8.2 -- Copyright 2002-2026, LAMP/EPFL`.

3. To type-check all Licorne and Scala examples at once, navigate to the `scripts` directory:
```sh
cd /opt/ranger-examples/scripts/
```
then run the timing script:
```sh
python3 timing_script.py
```
The script displays the compilation times while running, and writes the stdout and stderr outputs, as well as the compilation times, to files in the `/opt/ranger-examples/scripts/timing-runs` directory. The Licorne compiler prints errors to stderr, while the stdout file will contain only "Compiler not implemented" messages, which merely mean that the compiler stopped after type-checking because we currently have no backend.

**Note**: in the paper, we indicate that Licorne took 16s to compile all examples, while Scala took 34s. We obtained these results on a Windows machine (Lenovo ThinkPad, Windows 11, 64GB RAM, Intel Core Ultra 9 2.3 GHz). When running the same experiments on the same machine but using the Ubuntu-based Docker image, we got about the same compilation time for Licorne (15s), but the Scala compilation times fell down to about 21s. While we don't know the exact reason of this difference, this still supports the claim that we make in the paper that Ranger's typechecking process is fast enough to be practically usable.

4. To run the Checker Framework on all examples, navigate to the `java-checker-framework` directory:
```sh
cd /opt/ranger-examples/java-checker-framework
```
then compile the project (this automatically runs the Checker Framework):
```sh
mvn clean compile
```
Running this command for the first time will pull data from the Internet, so you'll need Internet connectivity for that part.

Issues found by the Checker Framework will be displayed in the console, so it is normal to get "ERROR" messages (we expect 21 of them, and it is expected that they get displayed twice).

5. To run the script that collects the formal comments in all units and builds the results table (table 2 in the paper), navigate to the `scripts` directory:
```sh
cd /opt/ranger-examples/scripts/
```
then run the script:
```sh
python3 comparison_script.py
```
We added the formal comments manually. Some are based on the verification results displayed by the tools, others are based on manual annotation counting. A precise description of the formal comments system that we used can be found in the [dedicated file](./examples/scripts/formal_comments_explanation.txt). The script additionally runs consistency checks and outputs some warnings to the console, referring to the fact that some units are not implemented in LiquidJava and other units are marked as "buggy" in the implementation in one of the frameworks but not in another framework. This is expected, because some annotations that one tool fails to verify may be inexpressible in another tool. The script also outputs a LaTeX version of the table to the console, and a CSV version to a file in the `out` directory. To display it:
```sh
cat /opt/ranger-examples/scripts/out/table.csv
```


## Verifying a single Ranger example

If you want to verify a single Licorne program, navigate to the `licorne` directory:
```sh
cd /opt/ranger-examples/licorne
```
then run the type-checker:
```sh
java -jar licorne-compiler.jar compile <example name>
```
E.g.:
```sh
java -jar licorne-compiler.jar compile arraymap/
```


## Verifying programs using LiquidJava

We used the LiquidJava VSCode extension to analyze programs using LiquidJava. VSCode and its LiquidJava extension are not installed in the Docker image, but should be easy to install on nearly any machine. If you want to replicate the results that we obtained using LiquidJava, install the LiquidJava VSCode extension from [its Visual Studio Marketplace page](https://marketplace.visualstudio.com/items?itemName=AlcidesFonseca.liquid-java), and open the file that you want to verify. LiquidJava will display error messages next to the code that it cannot verify. The Visual Studio Marketplace page of LiquidJava also provides additional information about the tool.


## Additional information about our experiments

We partitioned the code into *units* (most units are functions). Every unit is annotated according to a system of formal comments, whose precise description can be found [here](./examples/scripts/formal_comments_explanation.txt). 
The annotations specify, among others, the number of annotations used in that unit, whether or not the unit contains one or more bug(s), and whether or not the tool flags the unit (i.e. reports one or more bug(s) in that particular unit). 
Our [comparison script](./examples/scripts/comparison_script.py) traverses all files and collects the information specified by the formal comments. It outputs this information as a table, corresponding to table 2 in the paper. 
It dumps the LaTeX code of the table to the console, and produces a [CSV version](./examples/scripts/out/table.csv) in the [`scripts/out`](./examples/scripts/out/) directory (of course the links work only after the table has been generated).


## Note about the name of some experiments

The names given to some of our examples in the results table differ from the ones we give them in the source files. The following table maps both versions of the names to each other:
| Name in files | Name in table |
|---------------|---------------|
| Motivation    | Decoder       |
| ic4           | ThirdElem     |
| ic5           | ArrayLess     |
| ic7           | RemString     |
| ic9           | ArrayWrap     |
| lj1           | Fibonacci     |
| lj2           | Sum           |
| lj3           | AbsDiv        |
| lj4           | Car           |


## List of intentional bugs

One of our evaluation criteria being the behavior of the tools on faulty programs, we intentionally introduced bugs on some of our examples. Here is a list of those (the non-obvious ones are also indicated directly in the source code):
- ArrayMap, put: `<=` instead of `<` in the loop condition, causing `i` to possibly be out-of-bounds for the arrays
- Date, previousDay: `this.month() + 1` instead of `this.month() - 1` (simulating a copy-paste error)
- Time, moveBy: normalization of hours to 60 instead of 24
- FilterLess, filterLessThan_adHoc_buggy and filterLessThan_functional_buggy: the inequality in the filtering condition should be strict
- IC4, getThirdElement_1_buggy and getThirdElement_2_buggy: array access at index 3 for an array of length possibly less than 4
- IC5, lessThan_buggy: variable `i` is decremented instead of incremented
- IC7, removeSubstring_buggy: no check that indexOf returned a non-negative value (it returns -1 if the element is not found, which is not a valid index for the subsequent call to substring)
- LJ1, fib_original: the refinement `>= n` on the result does not hold (as mentioned in the comments, we did not find a way of expressing this constraint using the Checker Framework)
- LJ2, sum_original: same issue as LJ1, and here as well the Checker Framework fails to express the constraint
- LJ3, absDiv_buggy: does not force the divisor to be non-zero
- LJ4, test_buggy: passing 998 as an argument to method setYear, which expects a value >= 1801
- PositiveMax, maxPos_buggy: loop condition should use `<` instead of `<=`
- PositiveMax, maxPos_moreComplex_buggy: `incOdd` is allowed to be negative, thus `k` is not guaranteed to be a valid index for the array
- MergeSort, merge: decrementing `j` instead of incrementing it in the second branch of the if-elseif

