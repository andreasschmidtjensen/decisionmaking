% this agent prefers to only bid when not badInfo since if he bids, he prefers to be verified

Preferences:
	b => v.

Expectations:
	i => ~b.
	v => ~i.
	~v => ~b.
	~v && b => ~p.

Impossible states:
