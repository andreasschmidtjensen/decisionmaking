% this agent bids only when not badInfo since if he bids, he prefers to be verified

Preferences:
	bid(bob,lamp) => verified(bob).

Expectations:
	badInfo(bob) => ~bid(bob,lamp).
	verified(bob) => ~badInfo(bob).
	~verified(bob) => ~bid(bob,lamp).
	~verified(bob) && bid(bob,lamp) => ~participates(bob,lamp).

Impossible states:
