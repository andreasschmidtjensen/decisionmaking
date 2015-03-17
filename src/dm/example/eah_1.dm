% this agent bids only when not badInfo since if he bids, he prefers to be verified

Preferences:
	true => b && p.

Expectations:
	b => v.
	i => ~v.
	b && i => ~p.

Impossible states:
