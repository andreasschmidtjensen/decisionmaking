Preferences:
	bid(bob,lamp) => verified(bob).

Expectations:
	registered(bob) && bid(bob,lamp) => verified(bob).
	~verified(bob) && registered(bob) && bid(bob,lamp) => ~participates(bob,lamp).
	participates(bob,lamp) && viol(bob,buyer,obliged,verified(bob)) => viol(mike,manager,obliged,~participates(bob,lamp)).
	badInfo(bob) && registered(bob) => ~verified(bob).
	badInfo(bob) && registered(bob) && verified(bob) => viol(mike,manager,forbidden,verified(bob)).

Impossible states:
