(ns coercion-poc.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [coercion-poc.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[coercion-poc started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[coercion-poc has shut down successfully]=-"))
   :middleware wrap-dev})
