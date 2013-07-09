(ns macro-workshop-playground.core
  (:require clojure.walk)
  [:use clojure.tools.macro [clojure.walk :as walk]])
(def ... '())


;;;;
;;;; Funhouse
;;;;
 
(defn funhouse [forms] 
  ...)


;;;;
;;;; Macros
;;;;
 
(defmacro in-funhouse [forms] 
  ...)

(defmacro do-funhouse [& forms] 
  ...)


;;;;
;;;; Syntax-quote
;;;;

(defmacro debug [expr]
  ...)


;;;;
;;;; Multiple execution
;;;;

(defmacro and-1 [expr1 expr2]
  ...)


;;;;
;;;; Using functions in macros
;;;;

(defn stringify-static [parts]
  ...)

(defmacro pre-str [& parts]
  ...)


;;;;
;;;; Recursive macros
;;;;

(defmacro and-* 
  ([] ...)
  ([expr] ...)
  ([expr & more] ...))

