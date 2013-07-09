(ns macro-workshop-playground.core-test
  (:require [clojure.test :refer :all]
            [macro-workshop-playground.core :refer :all])
  (:use macro-workshop-playground.core))


;;;;
;;;; Funhouse
;;;;
 
;; 1. funhouse

(deftest funhouse-test
  (testing "funhouse"
    (is (= (funhouse '(+ 3 1)) '(- 3 1)))
    (is (= (funhouse '(- 3 1)) '(+ 3 1)))
    (is (= (funhouse '(* 3 1)) '(/ 3 1)))
    (is (= (funhouse '(/ 3 1)) '(* 3 1)))))

;; 2. Recursive funhouse

(deftest recursive-funhouse-test
  (testing "recursive funhouse"
    (is (= (funhouse '(+ 1 (/ 3 1))) '(- 1 (* 3 1))))))

;; 3. Extra credit

(deftest extra-credit-funhouse-test
  (testing "funhouse recursing into maps, vectors, and sets"
    (is (= (funhouse '(+ (/ 2 2)
                         (first #{(- 1 1)})
                         (first [(- 1 1)])
                         (:2nd {:1st 1, :2nd (+ 5 1)})))
           '(- (* 2 2)
               (first #{(+ 1 1)})
               (first [(+ 1 1)])
               (:2nd {:1st 1, :2nd (- 5 1)}))))))

;;;;
;;;; Macros
;;;;
 
;; 1. in-funhouse

(deftest in-funhouse-test
  (testing "in-funhouse"
    (is (= (in-funhouse (+ 6 1)) 5))))

;; 2. do-funhouse

(deftest do-funhouse-test
  (testing "do-funhouse"
    (with-local-vars [v nil]
      (is (= 1 (do-funhouse (var-set v (+ 1 2)) (* 2 2))))
      (is (= -1 @v)))))

;;;;
;;;; Syntax-quote
;;;;

;; 1. rewrite debug using syntax-quote

(deftest debug-test
  (testing "debug"
    (is (= 3 (debug (+ 1 2))))))

(deftest debug-variable-capture-test
  (testing "debug"
    (with-local-vars [v nil]
      (is (= 3 (let [println (fn [& args] (var-set v :busted))]
                 (debug (+ 1 2)))))
      (is (= nil @v)))))

;;;;
;;;; Multiple execution
;;;;

;; 1. fix multiple exection in debug

(deftest debug-single-eval-test
  (testing "debug only evaluates the expression once"
    (let [times (atom 0)]
      (is (= 3 (debug (do (swap! times inc) (+ 1 2)))))
      (is (= 1 @times)))))

;; 2. and–1

(deftest and-1-test
  (testing "and-1"
    (is (= :foo (and-1 true :foo)))
    (with-local-vars [v nil]
      (is (= nil (and-1 nil (var-set v "busted"))))
      (is (= nil @v)))))

;;;;
;;;; Using functions in macros
;;;;

;; 1. stringify-static

(deftest stringify-static-simple
  (testing "stringify-static"
    (is (= '(":foo" (- 3 4) "1" (+ 1 2) "jon" bar)
           (stringify-static '(:foo (- 3 4) 1 (+ 1 2) "jon" bar))))))

;; 2. pre-str

(deftest pre-str-test
  (testing "pre-str"
    (let [bar "baz"]
      (is (= "1 :foo 3 baz"
             (pre-str 1 " " :foo " " (+ 1 2) " " bar))))))

;; 2. Extra credit

(deftest stringify-static-extra-credit
  (testing "stringify-static collapses static runs"
    (is (= '(":foo 12jonboy3" (+ 1 2) bar "42")
           (stringify-static '(:foo " " 1 2 "jon"
                                    "boy" 3 (+ 1 2) bar 42))))))

;;;;
;;;; Recursive macros
;;;;

;; 1. and-*

(deftest and-*-test
  (testing "and-*"
    (is (= true (and-*)))
    (is (= 0 (and-* 0)))
    (is (= 1 (and-* 0 1)))
    (is (= 2 (and-* 0 1 2)))
    (with-local-vars [v nil]
      (is (= nil (and-* 0 nil (var-set v "busted"))))
      (is (= nil @v)))))

