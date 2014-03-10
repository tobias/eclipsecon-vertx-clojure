(let* ((base-dir (expand-file-name "~/presentations/eclipsecon/"))
       (preso-dir (concat base-dir "/presentation/"))
       (project-dir (concat base-dir "/examples/"))
       (src-dir (concat project-dir "src/examples/")))

  (find-file (concat preso-dir "vertx-clojure.org"))
  (org-reveal-export-to-html)

  (open-files-in-dir src-dir)
  (open-read-only (concat project-dir "project.clj"))
  (open-read-only (concat project-dir "java-src/tcrawley/examples/JavaHandler.java"))

  (dired project-dir)
  (shell)

  (shell-command "lein do javac, cljsbuild once")
  
  (shell-command (format "chromium-browser --new-window file:%svertx-clojure.html" preso-dir))
  
  (dolist (url '("http://localhost:8080" "http://localhost:8888"
                 "http://clojure.org/state" "https://github.com/vert-x/mod-lang-clojure"
                 "https://github.com/isaiah/lein-vertx" "https://github.com/stream1984/ring-vertx-adapter"))
    (shell-command (format "chromium-browser %s" url))))

