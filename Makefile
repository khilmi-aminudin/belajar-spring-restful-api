runMigrate:
    mysql --host=localhost --port=3306 --user=root --password=secret belajar_spring_restful_api < ./database.sql

.PHONY: runMigrate
