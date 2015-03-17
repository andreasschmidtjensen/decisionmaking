% this agent bids only when not badInfo since if he bids, he prefers to be verified

Preferences:
	~i => b.
	b => p.
	%true => b.

Expectations:
	b => v.
	i => ~v.
	b && i => ~p.

Impossible states:
	v && ~p.
