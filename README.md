# macro-workshop-playground

Welcome to the LambdaJam macro workshop. Install leiningen (http://leiningen.org/) and clone this repository.

If everything's working properly, you should be able to run:

    lein test

at your terminal within this repo and see the following output:

    lein test user

    Ran 0 tests containing 0 assertions.
    0 failures, 0 errors.

## Exercises

Empty function and macro shells for the exercises can be found in `src/macro_workshop_playground/core.clj` and commented out test cases for each can be found in `test/macro_workshop_playground/core_test.clj`

If you're new to Clojure development and/or leinengen open two terminals and in one run `lein repl` and save the other for running `lein test`.

To test-drive your work run uncomment a test case(s) for an exercise in `test/macro_workshop_playground/core_test.clj` and run `lein test` to see it fail, update the corresponding function or macro shell in `src/macro_workshop_playground/core.clj` and run `lein test` again to see if it passes.

You should also use the repl to experiment with Clojure functions and your macros - using macroexpand-1, for example. If you want to reload your source file from the repl call `(use :reload-all 'macro-workshop-playground.core)` or type `quit` and `lein repl` to restart it.

## References

cheatsheet.pdf includes most of the basic clojure functions you'll need for the exercises.

handout.pdf contains the workshop content.

For a more in-depth resource, see [the ClojureDocs quick reference](http://clojuredocs.org/quickref/Clojure%20Core).



## License

Copyright © 2013 Atomic Object

Distributed under the Eclipse Public License, the same as Clojure.
