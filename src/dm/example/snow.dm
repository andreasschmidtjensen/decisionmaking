Preferences:
	true => ~s.
	s => ~w.
	true => ~f.
	w => e.

Expectations:
	true => w.
	s => ~f && ~w.
	~s && ~w => f.
	true => ~e.
	w => ~f.

Impossible states:
	f && w.
	e && ~w.