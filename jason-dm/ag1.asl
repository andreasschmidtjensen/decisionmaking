// Agent ag1 in project jason-dm.mas2j

/* Initial beliefs and rules */
badInfo(bob).
registered(bob).

/* Initial goals */

!start.

/* Plans */

+!start : true <- !bid(bob, lamp).

+!bid(bob, lamp) : true <- .print("Bid placed.").

