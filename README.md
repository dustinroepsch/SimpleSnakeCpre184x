Compiling
==========

Windows
---------
Open a command prompt by typing `ctrl-r` typing `cmd` and hitting the enter key.

This should show a window like this (todo, put an image here).

navigate to the folder you downloaded the  code into by using the `cd` (change directory) command.
For example, if your username is dustin and you downloaded the code to your desktop, use `cd c:\Users\dustin\Desktop\SimpleSnakeCpre184x`

Once your command prompt is in the folder, use `./gradlew build shadowJar` to compile the program.


Mac OSX
---------
Open the terminal by typing `command space` to open spotlight search, typing `terminal` and then hitting enter.

Once the terminal is open, navigate to the folder you downloaded the code into using the `cd` (change directory) command.
For example, if you downloaded to your desktop, type `cd Desktop\SimpleSnakeCpre184x`

Once in the folder user `./gradlew build shadowJar` to compile the program.

Running
==========
To run the program, navigate to the same folder as above, and then run the command

`java -jar build/libs/SimpleSnake184x-1.0-all.jar`

This should open the game! Alternatively you could navigate to the libs folder in your file explorer, and double click on the jar file.