DEBUG ?= 0 # set DEBUG to 0 by default
# make DEBUG=1 to build debug build

#
# define compiler, directory and compiler flag variables
#

# JFLAGS = -g # JFLAGS not set, will default to empty string
JC = javac
ifeq ($(DEBUG), 1)
	BUILDDIR = ../../../debug
	JFLAGS = -g
else
	BUILDDIR = ../../../build
endif

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
# dependency levels
#

$(SYSTEMS): %.class: $(TASKS) $(UTILS)

$(TASKS): %.class: $(BUILDDIR)/Task.class

$(UTILS): %.class: $(BUILDDIR)/DukeException.class

#
# ignore files
#

.PHONY: clean debug

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
