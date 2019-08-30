DEBUG ?= 0 # set DEBUG to 0 by default
# make DEBUG=1 to build debug build

#
# define compiler, directory and compiler flag variables
# BUILDDIR is where Java builds to, CLASSDIR is where classes will be
#

# JFLAGS = -g # JFLAGS not set, will default to empty string
JC = javac
ifeq ($(DEBUG), 1)
	CLASSDIR = ../../../debug/aquohn/duke
	BUILDDIR = ../../../debug
	JFLAGS = -g
else
	CLASSDIR = ../../../build/aquohn/duke
	BUILDDIR = ../../../build
endif

_UTILS = DukeFatalException.class DukeResetException.class Task.class
UTILS = $(patsubst %,$(CLASSDIR)/%,$(_UTILS))

_TASKS = ToDo.class Event.class Deadline.class
TASKS = $(patsubst %,$(CLASSDIR)/%,$(_TASKS))

_TIMETASKS = Event.class Deadline.class
TIMETASKS = $(patsubst %,$(CLASSDIR)/%,$(_TIMETASKS))

_SYSTEMS = TaskList.class
SYSTEMS = $(patsubst %,$(CLASSDIR)/%,$(_SYSTEMS))

#
# define default compilation behaviour for class files
#

$(CLASSDIR)/%.class: %.java
	$(JC) $(JFLAGS) -d $(BUILDDIR) -cp $(BUILDDIR) $<

#
# default make target, will build dependencies on right of colon if needed
#

$(CLASSDIR)/Duke.class: $(SYSTEMS)

#
# dependency levels
#

$(SYSTEMS): %.class: $(TASKS) $(UTILS)

$(CLASSDIR)/TimedTask.class: $(UTILS)

$(TASKS): %.class: $(CLASSDIR)/Task.class

$(TIMETASKS): %.class: $(CLASSDIR)/TimedTask.class

$(UTILS): %.class: $(CLASSDIR)/DukeException.class

#
# ignore files
#

.PHONY: clean debug

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
