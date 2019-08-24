# Credits to: https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

#
# define compiler and compiler flag variables
#

JFLAGS = -g
JC = javac

#
# define default compilation behaviour for class files
#

.java.class:
	$(JC) $(JFLAGS) $<

#
# default make target, will build dependencies on right of colon if needed
#

Duke.class: ToDo.class Event.class Deadline.class

#
# these are all tasks, so Task will have to be built first
#

ToDo.class Event.class Deadline.class: Task.class

#
# ignore files
#

.PHONY: clean

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
