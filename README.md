[![Build Status](https://github.com/nesquikpro/monkey/actions/workflows/gradle.yml/badge.svg)](https://github.com/nesquikpro/monkey/actions/workflows/gradle.yml)

---

### Mods for Minecraft 1.8.9, written in Forge 2.x + Mixin

---

##  Mods

|                                       |                                                           |
|---------------------------------------|-----------------------------------------------------------|
| **AutoSprint**                        | Автоматический спринт без необходимости удерживать клавишу |
| **Clear Chat Messages**               | Очистка сообщений из чата                                 |
| **Copy Chat Message**                 | Возможность скопировать сообщение прямо из чата           |
| **Remove Footer & Header (Tab List)** | Убирает верхний и нижний баннер в списке игроков TAB      |
| **Custom GUI**                        | Для сервера Hypixel (QuickMod)                            |
| **Chat ❤ Replacer**                   | Автоматически заменяет `<3` на ❤️ при вводе               |
| **Remove Crosshair Blend**            | Убирает размытие/перекрытие цветов у прицела              |
| **Player Ping in Tab List**           | Отображает пинг игроков в списке TAB                      |
| **Always Night Mode**                 | Меняет время в мире на постоянную ночь                    |
| **No Foliage Render**                 | Убирает листву, траву и растительность для FPS и видимости |
| **Remove red numbers in Scoreboard**  | Удаляет красные цифры (очки) в Scoreboard                 |

##  Murder Mystery Mod

## Возможности

- **Подсветка в табе** — ник убийцы становится тёмно-красным
- **Оповещение в чат** — мод пишет имя убийцы в чат
- Поддержка режимов **Classic** и **Double**

## Как работает

Каждую секунду мод сканирует игроков в мире и проверяет,
держат ли они в руках предмет со скином ножа.
Если нож обнаружен — игрок помечается как убийца,
подсвечивается в табе и выводится сообщение в чат.

## Requirements

- Minecraft Forge **1.8.9**
- Java **8**
