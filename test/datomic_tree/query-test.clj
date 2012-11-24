(ns datomic-tree.query-test
  (:use datomic-tree.query
        [datomic.api :only [q db] :as d]))

(defonce url "datomic:mem://tree-test")

(d/delete-database url)
(d/create-database url)

(defonce conn (d/connect url))

(defonce schema (read-string (slurp "test/datomic/samples/seattle/seattle-schema.dtm")))

(d/transact conn schema)

(defonce data (map (fn [file-num] (-> (format "test/datomic/samples/seattle/seattle-data%d.dtm" file-num)
                                      slurp
                                      read-string))
                   (range 2)))

(doall (map #(d/transact conn %) data))

