% s=violation (sanction)

Preferences:
	true => b.
	p => ~s1.

Expectations:
	r && b => v.
	~v && r && b => s1.
	s1 => ~p.
	i && r => ~v.

	p && s1 => s2.
	i && r && v => s3.

Impossible states:
	~r && v.
	~r && p.
	~r && b.