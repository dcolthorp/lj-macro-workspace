(ns macro-workshop-playground.core
  (:require clojure.walk)
  [:use clojure.tools.macro [clojure.walk :as walk]])
(def ... '())


;;;;
;;;; Funhouse
;;;;

(defn funhouse [forms] 
  (postwalk-replace '{+ -, - +, * /, / *} forms))


;;;;
;;;; Macros
;;;;
 
(defmacro in-funhouse [forms] 
  (funhouse forms))

(defmacro do-funhouse [& forms] 
  (cons 'do (map funhouse forms)))


;;;;
;;;; Syntax-quote
;;;;

(defmacro debug [expr]
  `(let [x# ~expr]
     (println ~expr :> x#)
     x#))


;;;;
;;;; Multiple execution
;;;;

(defmacro and-1 [expr1 expr2]
  `(let [x# ~expr1]
    (if x#
      ~expr2
      x#)))


;;;;
;;;; Using functions in macros
;;;;

(defn stringify-static [parts]
  (let [static? (some-fn keyword? string? number?)
        partitioned (partition-by static? parts)]
    (mapcat (fn [coll]
              (if (static? (first coll))
                [(apply str coll)]
                coll))
            partitioned)))

(defmacro pre-str [& parts]
  `(str ~@(stringify-static parts)))


;;;;
;;;; Recursive macros
;;;;

(defmacro and-*
  ([] true)
  ([expr] expr)
  ([expr & exprs]
  `(let [x# ~expr]
    (if x#
      (and-* ~@exprs)
      x#))))

