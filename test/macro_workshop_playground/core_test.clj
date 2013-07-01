(ns macro-workshop-playground.core-test
  (:require [clojure.test :refer :all]
            [macro-workshop-playground.core :refer :all])
  (:use macro-workshop-playground.core))

; Funhouse
 
(deftest funhouse-test
  (testing "funhouse"
    (is (= (funhouse '(+ 3 1)) '(- 3 1)))
    (is (= (funhouse '(- 3 1)) '(+ 3 1)))
    (is (= (funhouse '(* 3 1)) '(/ 3 1)))
    (is (= (funhouse '(/ 3 1)) '(* 3 1)))))

(deftest recursive-funhouse-test
  (testing "recursive funhouse"
    (is (= (funhouse '(+ 1 (/ 3 1))) '(- 1 (* 3 1))))))

(deftest in-funhouse-test
  (testing "in-funhouse"
    (is (= (in-funhouse (+ 6 1)) 5))))

(deftest do-funhouse-test
  (testing "do-funhouse"
    (with-local-vars [v nil]
      (is (= 1 (do-funhouse (var-set v (+ 1 2)) (* 2 2))))
      (is (= -1 @v)))))


; Syntax-quote

(deftest debug-test
  (testing "debug"
    (is (= 3 (debug (+ 1 2))))))

(deftest debug-variable-capture-test
  (testing "debug"
    (with-local-vars [v nil]
      (is (= 3 (let [println (fn [& args] (var-set v :busted))]
                 (debug (+ 1 2)))))
      (is (= nil @v)))))

(deftest debug-single-eval-test
  (testing "debug only evaluates the expression once"
    (let [times (atom 0)]
      (is (= 3 (debug (do (swap! times inc) (+ 1 2)))))
      (is (= 1 @times)))))


(deftest and-1-test
  (testing "and-1"
    (is (= :foo (and-1 true :foo)))
    (with-local-vars [v nil]
      (is (= nil (and-1 nil (var-set v "busted"))))
      (is (= nil @v)))))

(deftest and-*-test
  (testing "and-*"
    (is (= 0 (and-* 0)))
    (is (= 1 (and-* 0 1)))
    (is (= 2 (and-* 0 1 2)))
    (with-local-vars [v nil]
      (is (= nil (and-* 0 nil (var-set v "busted"))))
      (is (= nil @v)))))

(deftest pre-str-test
  (testing "pre-str"
    (is (= "1 :foo 3 NO_SOURCE_PATH"
           (pre-str 1 " " :foo " " (+ 1 2) " " *file*)))))

