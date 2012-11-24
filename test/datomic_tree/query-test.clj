(ns datomic-tree.query-test
  (:use datomic-tree.query)
  (:require [datomic.api :as d]))

(defonce url "datomic:mem://tree-test")

(d/create-database url)

(defonce conn (d/connect url))

