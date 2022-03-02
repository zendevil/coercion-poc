(ns coercion-poc.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[coercion-poc started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[coercion-poc has shut down successfully]=-"))
   :middleware identity})
