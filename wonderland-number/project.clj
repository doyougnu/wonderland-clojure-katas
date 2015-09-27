(defproject wonderland-number "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :user {:plugins [[cider/cider-nrepl "0.9.0-SNAPSHOT"]
                   [refactor-nrepl "0.3.0-SNAPSHOT"]]}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [alembic "0.3.2"]
                 [org.clojure/tools.nrepl "0.2.7"]
                 [org.clojure/math.combinatorics "0.1.1"]])
