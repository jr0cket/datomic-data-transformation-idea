(ns datomic-tree.xml-zip
  (:use [clojure.data.zip.xml :only [xml-> text]])
  (:require [clojure.xml :as xml]
            [clojure.java.io :as io]
            [clojure.zip :as zip]
            ))

(defn parse-xml [xml-source]
  (xml/parse xml-source))

(parse-xml (io/file "/home/james/src/datomic-tree/test/datomic_tree/sample.xml"))

(defn zipper-for [f]
  (zip/xml-zip (xml/parse f)))

(def sample-file (io/file "/home/james/src/datomic-tree/test/datomic_tree/sample.xml"))

(let [zipper (zipper-for sample-file)]
  (xml-> zipper
         :book
         :trade
         :trade-id
         text))

(let [zipper (zipper-for sample-file)]
  (xml-> zipper
         :book
         :trade
         [:sub-trade :sub-trade-id text #(.startsWith % "E")]
         :trade-id
         text))

(let [zipper (zipper-for sample-file)]
  (xml-> zipper
         :book
         :trade
         :sub-trade
         [:sub-trade-leg text #(= "2" %)]
         [:sub-trade-id text #(.startsWith % "1")]
         :sub-trade-id
         text))

(let [zipper (zipper-for sample-file)]
  (xml-> zipper
         :book
         :trade
         :sub-trade
         [:sub-trade-leg text #(> 2 (Integer. %))]
         [:sub-trade-id text #(.startsWith % "E")]
         :sub-trade-id
         text))



