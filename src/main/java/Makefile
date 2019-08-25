# Credits to: https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

#
# define compiler and compiler flag variables
#

# JFLAGS = -g # JFLAGS not set, will default to empty string
JC = javac
BUILDDIR = ../../../build

_TASKS = ToDo.class Event.class Deadline.class
TASKS = $(patsubst %,$(BUILDDIR)/%,$(_TASKS))

#
# define default compilation behaviour for class files
#

$(BUILDDIR)/%.class: %.java
	$(JC) $(JFLAGS) -d $(BUILDDIR) -cp $(BUILDDIR) $<

#
# default make target, will build dependencies on right of colon if needed
#

$(BUILDDIR)/Duke.class: $(TASKS)

debug: JFLAGS = -g
debug: $(BUILDDIR)/Duke.class

#
# tasks, need to rebuilt if Task.java changes
#

$(TASKS): %.class: $(BUILDDIR)/Task.class

#
# ignore files
#

.PHONY: clean

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
