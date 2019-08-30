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

SRCDIR = .

#
# find all.javas, list of source files
#

# all_javas - Temp file for holding source file list
all_javas := all.javas

#
# define default compilation behaviour for class files
#

compile: all.javas
	$(JC) $(JFLAGS) -d $(BUILDDIR) -cp $(BUILDDIR) @$<

# all_javas - Gather source file list
#.INTERMEDIATE: $(all_javas)
#$(all_javas):
#	$(FIND) $(SOURCE_DIR) -name '*.java' > $@

#
# ignore files
#

.PHONY: clean debug compile

#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class
