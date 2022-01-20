INSERT INTO users (username, email, is_admin) VALUES
	('admin', 'admin@localhost', true),
	('guest', 'guest@localhost', false);

INSERT INTO vulns (name, severity) VALUES
	('SQL_Injection',                           'high'),
	('Stored_XSS',                              'high'),
	('Reflected_XSS_All_Clients',               'high'),
	('CGI_Reflected_XSS_All_Clients',           'high'),
	
	('Unsafe_Object_Binding',                   'medium'),
	('Trust_Boundary_Violation',                'medium'),
	('Heap_Inspection',                         'medium'),
	('HTTP_Response_Splitting',                 'medium'),
	('Unchecked_Input_for_Loop_Condition',      'medium'),
	('Improper_Restriction_of_XXE_Ref',         'medium'),
	('Improper_Restriction_of_Stored_XXE_Ref',  'medium'),
	('Spring_ModelView_Injection',              'medium'),
	('SSRF',                                    'medium'),
	('Unvalidated_Forwards',                    'medium'),
	('Use_of_Hard_coded_Cryptographic_Key',     'medium'),
	('Cross_Site_History_Manipulation',         'medium'),
	('Dangerous_File_Inclusion',                'medium'),
	('XSRF/CSRF',                               'medium'),
	('Client_Potential_XSS',                    'medium'),
	('Use_of_Insufficiently_Random_Values',     'medium'),
	('Use_of_Cryptographically_Weak_PRNG',      'medium'),
	('Privacy_Violation',                       'medium'),
	('HttpOnlyCookies',                         'medium'),
	('HttpOnlyCookies_In_Config',               'medium'),
	('Angular_Improper_Type_Pipe_Usage',        'medium'),
	('Client_DOM_XSS',                          'medium'),
	('Frameable_Login_Page',                    'medium'),
	('Missing_HSTS_Header',                     'medium'),
	('Input_Path_Not_Canonicalized',            'medium'),
	('Unvalidated_SSL_Certificate_Hostname',    'medium');