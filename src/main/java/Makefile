# Credits to: https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

#
# define compiler and compiler flag variables
#

# JFLAGS = -g # JFLAGS not set, will default to empty string
JC = javac
BUILDDIR = ../../../build

_UTILS = DukeFatalException.class DukeResetException.class Task.class
UTILS = $(patsubst %,$(BUILDDIR)/%,$(_UTILS))

_TASKS = ToDo.class Event.class Deadline.class
TASKS = $(patsubst %,$(BUILDDIR)/%,$(_TASKS))

_SYSTEMS = TaskList.class
SYSTEMS = $(patsubst %,$(BUILDDIR)/%,$(_SYSTEMS))

#
# define default compilation behaviour for class files
#

$(BUILDDIR)/%.class: %.java
	$(JC) $(JFLAGS) -d $(BUILDDIR) -cp $(BUILDDIR) $<

#
# default make target, will build dependencies on right of colon if needed
#

$(BUILDDIR)/Duke.class: $(SYSTEMS)

#
# "make debug" to build Duke.class with these options
#

debug: JFLAGS = -g
debug: $(BUILDDIR)/Duke.class

#
# dependency levels
#

$(SYSTEMS): %.class: $(TASKS) $(UTILS)

$(TASKS): %.class: $(BUILDDIR)/Task.class

$(UTILS): %.class: $(BUILDDIR)/DukeException.class

#
# ignore files
#

.PHONY: clean

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
