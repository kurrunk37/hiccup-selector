(ns hiccup-selector.core
  (:require clojure.test))

(defn sql->fun
  "把结构化的查询条件转为函数"
  [sql]
  ;(println "sql->fun" sql)
  (fn [avg] (every? true? 
    (for [[k v] sql]
      (case k
        :tag
          (= (first avg) v)
        :class
          (contains? (clojure.string/split (get (get avg 1 {}) :class "") #"[\s]+") v)
        (= (get (get avg 1 {}) k) v))))))
        
                    

(defn select
  "选择器入口
  x: [s1 s2 s3 ... select-node]
  s: 
    1. fn:#(and (= (first %) :html) (in (get % 2) :class))
    2. map : {:tag :div, :class \"content\" :id \"content\"}
  "
  [doc query-list]
  (let [q (first query-list)
        match? (if (clojure.test/function? q) q (sql->fun q))]
        (println q "match?" match?)
        (println (match? doc))
    (if (match? doc)
      (if (= (count query-list) 1)
        doc
        (filter #(not (nil? %)) (for [subnode (drop 2 doc)] (select subnode (rest query-list))))))))

(defn foo
  "I don't do a whole lot."
  [& x]
  (println "a")
  (println (sql->fun {:tag :html , :class "mid" , :id "eleid"}))
  (println "b")
  (let [doc [:html {} [:head {}] [:body {:class "bc"} "bodycontent"]]]
    (println "select:" (select doc (map sql->fun [{:tag :html} {:tag :body}]))))
  (println "Hello, World!"))
