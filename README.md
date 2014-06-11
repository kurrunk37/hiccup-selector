hiccup-selector
===============

这是一个hiccup数据选择器

## 使用方法

```clojure
(def doc [:html {} [:head {}] [:body {:class "bc"} "bodycontent"]])

(select doc [{:tag :body}])

(select doc [{:class "bc"}])
```
