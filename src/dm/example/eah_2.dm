% this preference makes the agent bid when not badInfo about him, since he wants to participate when having bid

Preferences:
	true => b && p.

Expectations:
	i => ~b.
	v => ~i.
	~v => ~b.
	~v && b => ~p.

Impossible states:
