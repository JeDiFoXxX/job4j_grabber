# job4j_grabber
## Описание проекта

Проект **job4j_grabber** — это агрегатор вакансий, который автоматически собирает объявления о работе с различных сайтов и сохраняет их в базу данных.

---

## Основные возможности

- Запуск по расписанию — **раз в минуту**.
- Период запуска настраивается в файле `app.properties`.
- Первый источник вакансий — сайт `https://career.habr.com/vacancies/java_developer`.
- Программа считывает все вакансии с первых 5 страниц, относящиеся к Java, и сохраняет их в базу.

---

## Расширения и архитектура

- Поддержка добавления новых сайтов **без изменения кода** — реализовано через конфигурацию и абстракции.
- Возможность **параллельного парсинга** нескольких сайтов для повышения производительности.

---

## Технологии и инструменты

- **Maven** — управление зависимостями и сборка проекта.
- **JaCoCo** — анализ покрытия тестами.
- **Checkstyle** — проверка соответствия кода стилю.

---
