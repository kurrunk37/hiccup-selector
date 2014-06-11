hiccup-selector
===============

这是一个hiccup数据选择器

## 使用方法

```clojure
(def doc [:html {} [:head {}] [:body {:class "bc"} [:div {:class "box" :id "content"} [:img {:src "test"}] [:img {:src "this"]] "bodycontent"]])

(select doc [{:tag :body}])

(select doc [{:class "bc"}])

(select doc [{:id "content"} {:src "test"}])
```
