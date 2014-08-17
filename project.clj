(defproject stepmania-js "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/dommy "0.1.3"]
                 [cljs-ajax "0.2.6"]
                 [org.clojure/clojurescript "0.0-2311"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.4.0"]]
  :cljsbuild {:builds {:dev
                       {:id "dev"
                        :source-paths ["src/cljs"]
                        :jar true
                        :compiler {:externs ["externs/pixi_externs.js"]
                                   :pretty-print true
                                   :optimizations :none
                                   :output-to "html/js/stepmania_js_dev.js"
                                   :source-map "html/js/stepmania_js_dev.js.map"
                                   :output-dir "html/js/dev"}}
                       :prod
                       {:id "prod"
                        :source-paths ["src/cljs"]
                        :jar true
                        :compiler {:externs ["externs/pixi_externs.js"]
                                   :pretty-print true
                                   :optimizations :advanced
                                   :output-to "html/js/stepmania_js.js"
                                   :source-map "html/js/stepmania_js.js.map"
                                   :output-dir "html/js/prod"}}}})
