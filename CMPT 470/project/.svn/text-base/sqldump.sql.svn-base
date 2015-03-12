--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auth_group; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_group (
    id integer NOT NULL,
    name character varying(80) NOT NULL
);


ALTER TABLE public.auth_group OWNER TO project;

--
-- Name: auth_group_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_group_id_seq OWNER TO project;

--
-- Name: auth_group_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_group_id_seq OWNED BY auth_group.id;


--
-- Name: auth_group_permissions; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_group_permissions (
    id integer NOT NULL,
    group_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE public.auth_group_permissions OWNER TO project;

--
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_group_permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_group_permissions_id_seq OWNER TO project;

--
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_group_permissions_id_seq OWNED BY auth_group_permissions.id;


--
-- Name: auth_permission; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_permission (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    content_type_id integer NOT NULL,
    codename character varying(100) NOT NULL
);


ALTER TABLE public.auth_permission OWNER TO project;

--
-- Name: auth_permission_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_permission_id_seq OWNER TO project;

--
-- Name: auth_permission_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_permission_id_seq OWNED BY auth_permission.id;


--
-- Name: auth_user; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_user (
    id integer NOT NULL,
    password character varying(128) NOT NULL,
    last_login timestamp with time zone NOT NULL,
    is_superuser boolean NOT NULL,
    username character varying(30) NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(75) NOT NULL,
    is_staff boolean NOT NULL,
    is_active boolean NOT NULL,
    date_joined timestamp with time zone NOT NULL
);


ALTER TABLE public.auth_user OWNER TO project;

--
-- Name: auth_user_groups; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_user_groups (
    id integer NOT NULL,
    user_id integer NOT NULL,
    group_id integer NOT NULL
);


ALTER TABLE public.auth_user_groups OWNER TO project;

--
-- Name: auth_user_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_user_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_user_groups_id_seq OWNER TO project;

--
-- Name: auth_user_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_user_groups_id_seq OWNED BY auth_user_groups.id;


--
-- Name: auth_user_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_user_id_seq OWNER TO project;

--
-- Name: auth_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_user_id_seq OWNED BY auth_user.id;


--
-- Name: auth_user_user_permissions; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE auth_user_user_permissions (
    id integer NOT NULL,
    user_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE public.auth_user_user_permissions OWNER TO project;

--
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE auth_user_user_permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_user_user_permissions_id_seq OWNER TO project;

--
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE auth_user_user_permissions_id_seq OWNED BY auth_user_user_permissions.id;


--
-- Name: django_admin_log; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE django_admin_log (
    id integer NOT NULL,
    action_time timestamp with time zone NOT NULL,
    user_id integer NOT NULL,
    content_type_id integer,
    object_id text,
    object_repr character varying(200) NOT NULL,
    action_flag smallint NOT NULL,
    change_message text NOT NULL,
    CONSTRAINT django_admin_log_action_flag_check CHECK ((action_flag >= 0))
);


ALTER TABLE public.django_admin_log OWNER TO project;

--
-- Name: django_admin_log_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE django_admin_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.django_admin_log_id_seq OWNER TO project;

--
-- Name: django_admin_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE django_admin_log_id_seq OWNED BY django_admin_log.id;


--
-- Name: django_content_type; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE django_content_type (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    app_label character varying(100) NOT NULL,
    model character varying(100) NOT NULL
);


ALTER TABLE public.django_content_type OWNER TO project;

--
-- Name: django_content_type_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE django_content_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.django_content_type_id_seq OWNER TO project;

--
-- Name: django_content_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE django_content_type_id_seq OWNED BY django_content_type.id;


--
-- Name: django_migrations; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE django_migrations (
    id integer NOT NULL,
    app character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    applied timestamp with time zone NOT NULL
);


ALTER TABLE public.django_migrations OWNER TO project;

--
-- Name: django_migrations_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE django_migrations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.django_migrations_id_seq OWNER TO project;

--
-- Name: django_migrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE django_migrations_id_seq OWNED BY django_migrations.id;


--
-- Name: django_session; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE django_session (
    session_key character varying(40) NOT NULL,
    session_data text NOT NULL,
    expire_date timestamp with time zone NOT NULL
);


ALTER TABLE public.django_session OWNER TO project;

--
-- Name: website_savedplaces; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE website_savedplaces (
    id integer NOT NULL,
    trip_id integer NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(500) NOT NULL,
    website character varying(500) NOT NULL,
    phone character varying(20) NOT NULL,
    rating character varying(10) NOT NULL
);


ALTER TABLE public.website_savedplaces OWNER TO project;

--
-- Name: website_savedplaces_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE website_savedplaces_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.website_savedplaces_id_seq OWNER TO project;

--
-- Name: website_savedplaces_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE website_savedplaces_id_seq OWNED BY website_savedplaces.id;


--
-- Name: website_trip; Type: TABLE; Schema: public; Owner: project; Tablespace: 
--

CREATE TABLE website_trip (
    id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying(255) NOT NULL,
    start character varying(255) NOT NULL,
    "end" character varying(255) NOT NULL,
    share_type smallint NOT NULL,
    CONSTRAINT website_trip_share_type_check CHECK ((share_type >= 0))
);


ALTER TABLE public.website_trip OWNER TO project;

--
-- Name: website_trip_id_seq; Type: SEQUENCE; Schema: public; Owner: project
--

CREATE SEQUENCE website_trip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.website_trip_id_seq OWNER TO project;

--
-- Name: website_trip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: project
--

ALTER SEQUENCE website_trip_id_seq OWNED BY website_trip.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_group ALTER COLUMN id SET DEFAULT nextval('auth_group_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_group_permissions ALTER COLUMN id SET DEFAULT nextval('auth_group_permissions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_permission ALTER COLUMN id SET DEFAULT nextval('auth_permission_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user ALTER COLUMN id SET DEFAULT nextval('auth_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_groups ALTER COLUMN id SET DEFAULT nextval('auth_user_groups_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_user_permissions ALTER COLUMN id SET DEFAULT nextval('auth_user_user_permissions_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY django_admin_log ALTER COLUMN id SET DEFAULT nextval('django_admin_log_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY django_content_type ALTER COLUMN id SET DEFAULT nextval('django_content_type_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY django_migrations ALTER COLUMN id SET DEFAULT nextval('django_migrations_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY website_savedplaces ALTER COLUMN id SET DEFAULT nextval('website_savedplaces_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: project
--

ALTER TABLE ONLY website_trip ALTER COLUMN id SET DEFAULT nextval('website_trip_id_seq'::regclass);


--
-- Data for Name: auth_group; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_group (id, name) FROM stdin;
\.


--
-- Name: auth_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_group_id_seq', 1, false);


--
-- Data for Name: auth_group_permissions; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_group_permissions (id, group_id, permission_id) FROM stdin;
\.


--
-- Name: auth_group_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_group_permissions_id_seq', 1, false);


--
-- Data for Name: auth_permission; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_permission (id, name, content_type_id, codename) FROM stdin;
1	Can add log entry	1	add_logentry
2	Can change log entry	1	change_logentry
3	Can delete log entry	1	delete_logentry
4	Can add permission	2	add_permission
5	Can change permission	2	change_permission
6	Can delete permission	2	delete_permission
7	Can add group	3	add_group
8	Can change group	3	change_group
9	Can delete group	3	delete_group
10	Can add user	4	add_user
11	Can change user	4	change_user
12	Can delete user	4	delete_user
13	Can add content type	5	add_contenttype
14	Can change content type	5	change_contenttype
15	Can delete content type	5	delete_contenttype
16	Can add session	6	add_session
17	Can change session	6	change_session
18	Can delete session	6	delete_session
19	Can add trip	7	add_trip
20	Can change trip	7	change_trip
21	Can delete trip	7	delete_trip
22	Can add saved places	8	add_savedplaces
23	Can change saved places	8	change_savedplaces
24	Can delete saved places	8	delete_savedplaces
\.


--
-- Name: auth_permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_permission_id_seq', 24, true);


--
-- Data for Name: auth_user; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_user (id, password, last_login, is_superuser, username, first_name, last_name, email, is_staff, is_active, date_joined) FROM stdin;
1	pbkdf2_sha256$12000$pRYOyjRvvttP$pJzVmEITEZlx6gGanUhkWQn8uRvuemtrZG29g+gcK/Q=	2014-04-10 18:24:50.577741-07	f	joel				f	t	2014-04-10 18:24:50.384629-07
2	pbkdf2_sha256$12000$Rt6VC4PmPSO8$QsNPu+N6RS5Yvv34ey3UXGYzmdNhCH8Y8XbKM2stYrQ=	2014-04-10 18:31:33.073281-07	f	ktest				f	t	2014-04-10 18:31:32.89364-07
3	pbkdf2_sha256$12000$QrWPRzOr0J04$z24u2cy43RsFgK6WDdOH4rCMNkhdhvNhg+imE3CUW7U=	2014-04-10 21:16:39.325555-07	f	abc				f	t	2014-04-10 21:16:39.147475-07
4	pbkdf2_sha256$12000$0b9fIGzTWyKf$4UdfVtUMDCsI0Gm2yzu0NsWXZjAM++0o1llqO3859h0=	2014-04-10 22:05:27.735403-07	f	kristina				f	t	2014-04-10 21:37:17.182335-07
5	pbkdf2_sha256$12000$Kl09iExI1oEO$Nx4kA7OfESe1HptrAfUk7KJLSm2nq53ADD8YIfhWmiI=	2014-04-10 22:09:06.981306-07	f	testuser				f	t	2014-04-10 22:07:01.793531-07
\.


--
-- Data for Name: auth_user_groups; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_user_groups (id, user_id, group_id) FROM stdin;
\.


--
-- Name: auth_user_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_user_groups_id_seq', 1, false);


--
-- Name: auth_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_user_id_seq', 5, true);


--
-- Data for Name: auth_user_user_permissions; Type: TABLE DATA; Schema: public; Owner: project
--

COPY auth_user_user_permissions (id, user_id, permission_id) FROM stdin;
\.


--
-- Name: auth_user_user_permissions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('auth_user_user_permissions_id_seq', 1, false);


--
-- Data for Name: django_admin_log; Type: TABLE DATA; Schema: public; Owner: project
--

COPY django_admin_log (id, action_time, user_id, content_type_id, object_id, object_repr, action_flag, change_message) FROM stdin;
\.


--
-- Name: django_admin_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('django_admin_log_id_seq', 1, false);


--
-- Data for Name: django_content_type; Type: TABLE DATA; Schema: public; Owner: project
--

COPY django_content_type (id, name, app_label, model) FROM stdin;
1	log entry	admin	logentry
2	permission	auth	permission
3	group	auth	group
4	user	auth	user
5	content type	contenttypes	contenttype
6	session	sessions	session
7	trip	website	trip
8	saved places	website	savedplaces
\.


--
-- Name: django_content_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('django_content_type_id_seq', 8, true);


--
-- Data for Name: django_migrations; Type: TABLE DATA; Schema: public; Owner: project
--

COPY django_migrations (id, app, name, applied) FROM stdin;
\.


--
-- Name: django_migrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('django_migrations_id_seq', 1, false);


--
-- Data for Name: django_session; Type: TABLE DATA; Schema: public; Owner: project
--

COPY django_session (session_key, session_data, expire_date) FROM stdin;
uryxvnm84nmnj3hh6hp1fzkuumg8t6vm	ZTUxZjU0ZmJmNmNkNmY4NWFkMDBjNjcwYTcxNTkwZTVkYTY5YTlhYzp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6Mn0=	2014-04-24 18:31:33.075993-07
bn9ppcno44jr63jqr7pzqjq0v5t6s0q7	MzBjMWIzNDczMWE3ZDkxNGJlN2QzMzliMDA5ZjU3NDcwYTRiN2I3OTp7fQ==	2014-04-24 19:59:15.225131-07
w8y1mdpsuuve326ypufdjcpfvppetc1p	NWUwZjgzZjNkYTQzYjNiNWJkNWVmZDMyYzUxNjBlNzAxY2JlOGNkODp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6NH0=	2014-04-24 21:59:34.179-07
p80zincn05hy9oth0kpi4l77mj462jkg	NWUwZjgzZjNkYTQzYjNiNWJkNWVmZDMyYzUxNjBlNzAxY2JlOGNkODp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6NH0=	2014-04-24 22:02:25.94001-07
1xb03ny6iza8pbfhag34xp7xp9okm4oz	NWUwZjgzZjNkYTQzYjNiNWJkNWVmZDMyYzUxNjBlNzAxY2JlOGNkODp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6NH0=	2014-04-24 22:05:27.742931-07
lp9opv8h1ic4465l2emmy6mn5sk3ucws	N2RjNDZkMmZmYjg0NmQ2YWU2MTI0MThjMDY3MWE1Mzg3YTM3N2YwOTp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6NX0=	2014-04-24 22:09:06.98417-07
\.


--
-- Data for Name: website_savedplaces; Type: TABLE DATA; Schema: public; Owner: project
--

COPY website_savedplaces (id, trip_id, name, address, website, phone, rating) FROM stdin;
1	1	Rasar State Park	38730 Cape Horn Rd, Concrete, WA, United States	http://www.parks.wa.gov/parks/?selectedpark=Rasar&subject=all	(360) 826-3942	
2	1	Museum of Northwest Art	121 1st St, La Conner, WA, United States	http://www.museumofnwart.org/	(360) 466-4446	
4	3	Rolley Lake Provincial Park	Mission, BC, Canada	http://www.env.gov.bc.ca/bcparks/explore/parkpgs/rolley.html	(519) 826-6850	
5	3	Chilliwack River Provincial Park	Rosedale, BC, Canada	http://www.env.gov.bc.ca/bcparks/explore/parkpgs/chilliwack_rv/		
6	4	Rasar State Park	38730 Cape Horn Rd, Concrete, WA, United States	http://www.parks.wa.gov/parks/?selectedpark=Rasar&subject=all	(360) 826-3942	
9	4	South Whidbey Island State Park	4128 S Smugglers Cove Rd, Freeland, WA, United States	http://www.parks.wa.gov/parks/?selectedpark=South%20Whidbey&subject=all	(360) 331-4559	
10	8	Flying Heritage Collection	3407 109th St SW, Everett, WA, United States	http://www.flyingheritage.com/	(206) 342-4242	4.3
13	12	Adam's Northwest Bistro & Brewery	104 N Lewis St, Monroe, WA, United States	http://www.adamsnwbistro.com/	(360) 794-4056	4.1
14	12	Birch Bay State Park	5105 Helweg Rd, Blaine, WA, United States	http://www.parks.wa.gov/	(360) 371-2800	4
15	12	Aberdeen Centre	4151 Hazelbridge Way, Richmond, BC, Canada	http://www.aberdeencentre.com/en/index.php	(604) 270-1234	3.7
16	12	Museum of Northwest Art	121 1st St, La Conner, WA, United States	http://www.museumofnwart.org/	(360) 466-4446	
17	13	Meerkerk Gardens	3531 Meerkerk Ln, Greenbank, WA, United States	http://www.meerkerkgardens.org/	(360) 678-1912	
18	13	Flying Heritage Collection	3407 109th St SW, Everett, WA, United States	http://www.flyingheritage.com/	(206) 342-4242	4.3
19	12	The Chrysalis Inn & Spa	804 10th St, Bellingham, WA, United States	http://www.thechrysalisinn.com/	(360) 756-1005	4.5
20	12	SUBWAY® Restaurants	1807 Main St, Lake Stevens, WA, United States	http://www.subway.com/subwayroot/default.aspx?rdr=Banners:GooglePlaces:SubwayCom	(425) 335-9938	
21	14	Palmer House A Hilton Hotel	17 E Monroe St, Chicago, IL, United States	http://www3.hilton.com/en/hotels/illinois/palmer-house-a-hilton-hotel-CHIPHHH/index.html	(312) 726-7500	4.1
22	15	The Westin Seattle	1900 5th Ave, Seattle, WA, United States	http://www.westinseattle.com/	(206) 728-1000	4.2
23	15	Billings Hotel and Convention Center	1223 Mullowney Ln, Billings, Mt, United States	http://www.billingshotel.com/	(406) 248-7151	3.2
24	15	The Depot Renaissance Minneapolis Hotel	225 3rd Ave S, Minneapolis, MN, United States		(612) 375-1700	4.2
25	15	Palmer House A Hilton Hotel	17 E Monroe St, Chicago, IL, United States	http://www3.hilton.com/en/hotels/illinois/palmer-house-a-hilton-hotel-CHIPHHH/index.html	(312) 726-7500	4.1
26	16	Billings Hotel and Convention Center	1223 Mullowney Ln, Billings, Mt, United States	http://www.billingshotel.com/	(406) 248-7151	3.2
27	16	The Westin Seattle	1900 5th Ave, Seattle, WA, United States	http://www.westinseattle.com/	(206) 728-1000	4.2
28	16	The Depot Renaissance Minneapolis Hotel	225 3rd Ave S, Minneapolis, MN, United States		(612) 375-1700	4.2
29	17	BEST WESTERN PLUS Heritage Inn	151 McLeod Rd, Bellingham, WA, United States	http://www.bestwesternheritageinn.com/	(360) 647-1912	3.7
30	17	Skagit Valley Casino Resort	5984 N Darrk Ln, Bow, WA, United States	http://www.theskagit.com/	(360) 724-7777	3.4
31	17	Deception Pass State Park	41229 Washington 20, Oak Harbor, WA, United States	http://www.parks.wa.gov/	(360) 675-2417	4.7
32	17	South Whidbey Island State Park	4128 S Smugglers Cove Rd, Freeland, WA, United States	http://www.parks.wa.gov/parks/?selectedpark=South%20Whidbey&subject=all	(360) 331-4559	
33	17	Embassy Suites Seattle - North/Lynnwood	20610 44th Ave W, Lynnwood, WA, United States	http://embassysuites3.hilton.com/en/hotels/washington/embassy-suites-seattle-north-lynnwood-SEALWES/index.html	(425) 775-2500	4.3
\.


--
-- Name: website_savedplaces_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('website_savedplaces_id_seq', 33, true);


--
-- Data for Name: website_trip; Type: TABLE DATA; Schema: public; Owner: project
--

COPY website_trip (id, user_id, name, start, "end", share_type) FROM stdin;
1	1		Vancouver, BC, Canada	Seattle, WA, United States	0
2	2		Vancouver, BC, Canada	Kelowna, BC, Canada	0
3	2		Vancouver, BC, Canada	Kelowna, BC, Canada	0
4	3		Vancouver, BC, Canada	Seattle, WA, United States	0
5	3		Vancouver, BC, Canada	Toronto, ON, Canada	0
6	3		Vancouver, BC, Canada	Seattle, WA, United States	0
7	3		Vancouver, BC, Canada	Seattle, WA, United States	0
8	3		Vancouver, BC, Canada	Seattle, WA, United States	0
9	3		Vancouver, BC, Canada	Seattle, WA, United States	0
10	3		Vancouver, BC, Canada	Seattle, WA, United States	0
12	4		Vancouver, BC, Canada	Seattle, WA, United States	0
13	4		hi	Seattle, WA, United States	2
14	3		Vancouver, BC, Canada	Toronto, ON, Canada	0
15	3		Vancouver, BC, Canada	Toronto, ON, Canada	0
16	4		Vancouver, BC, Canada	Chicago, IL, United States	0
17	5		Vancouver, BC, Canada	Seattle, WA, United States	0
\.


--
-- Name: website_trip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: project
--

SELECT pg_catalog.setval('website_trip_id_seq', 17, true);


--
-- Name: auth_group_name_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_group
    ADD CONSTRAINT auth_group_name_key UNIQUE (name);


--
-- Name: auth_group_permissions_group_id_permission_id_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_group_id_permission_id_key UNIQUE (group_id, permission_id);


--
-- Name: auth_group_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_pkey PRIMARY KEY (id);


--
-- Name: auth_group_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_group
    ADD CONSTRAINT auth_group_pkey PRIMARY KEY (id);


--
-- Name: auth_permission_content_type_id_codename_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT auth_permission_content_type_id_codename_key UNIQUE (content_type_id, codename);


--
-- Name: auth_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT auth_permission_pkey PRIMARY KEY (id);


--
-- Name: auth_user_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_pkey PRIMARY KEY (id);


--
-- Name: auth_user_groups_user_id_group_id_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_user_id_group_id_key UNIQUE (user_id, group_id);


--
-- Name: auth_user_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT auth_user_pkey PRIMARY KEY (id);


--
-- Name: auth_user_user_permissions_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_pkey PRIMARY KEY (id);


--
-- Name: auth_user_user_permissions_user_id_permission_id_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_user_id_permission_id_key UNIQUE (user_id, permission_id);


--
-- Name: auth_user_username_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT auth_user_username_key UNIQUE (username);


--
-- Name: django_admin_log_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT django_admin_log_pkey PRIMARY KEY (id);


--
-- Name: django_content_type_app_label_model_key; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY django_content_type
    ADD CONSTRAINT django_content_type_app_label_model_key UNIQUE (app_label, model);


--
-- Name: django_content_type_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY django_content_type
    ADD CONSTRAINT django_content_type_pkey PRIMARY KEY (id);


--
-- Name: django_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY django_migrations
    ADD CONSTRAINT django_migrations_pkey PRIMARY KEY (id);


--
-- Name: django_session_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY django_session
    ADD CONSTRAINT django_session_pkey PRIMARY KEY (session_key);


--
-- Name: website_savedplaces_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY website_savedplaces
    ADD CONSTRAINT website_savedplaces_pkey PRIMARY KEY (id);


--
-- Name: website_trip_pkey; Type: CONSTRAINT; Schema: public; Owner: project; Tablespace: 
--

ALTER TABLE ONLY website_trip
    ADD CONSTRAINT website_trip_pkey PRIMARY KEY (id);


--
-- Name: auth_group_name_like; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_group_name_like ON auth_group USING btree (name varchar_pattern_ops);


--
-- Name: auth_group_permissions_group_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_group_permissions_group_id ON auth_group_permissions USING btree (group_id);


--
-- Name: auth_group_permissions_permission_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_group_permissions_permission_id ON auth_group_permissions USING btree (permission_id);


--
-- Name: auth_permission_content_type_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_permission_content_type_id ON auth_permission USING btree (content_type_id);


--
-- Name: auth_user_groups_group_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_user_groups_group_id ON auth_user_groups USING btree (group_id);


--
-- Name: auth_user_groups_user_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_user_groups_user_id ON auth_user_groups USING btree (user_id);


--
-- Name: auth_user_user_permissions_permission_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_user_user_permissions_permission_id ON auth_user_user_permissions USING btree (permission_id);


--
-- Name: auth_user_user_permissions_user_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_user_user_permissions_user_id ON auth_user_user_permissions USING btree (user_id);


--
-- Name: auth_user_username_like; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX auth_user_username_like ON auth_user USING btree (username varchar_pattern_ops);


--
-- Name: django_admin_log_content_type_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX django_admin_log_content_type_id ON django_admin_log USING btree (content_type_id);


--
-- Name: django_admin_log_user_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX django_admin_log_user_id ON django_admin_log USING btree (user_id);


--
-- Name: django_session_expire_date; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX django_session_expire_date ON django_session USING btree (expire_date);


--
-- Name: django_session_session_key_like; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX django_session_session_key_like ON django_session USING btree (session_key varchar_pattern_ops);


--
-- Name: website_savedplaces_trip_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX website_savedplaces_trip_id ON website_savedplaces USING btree (trip_id);


--
-- Name: website_trip_user_id; Type: INDEX; Schema: public; Owner: project; Tablespace: 
--

CREATE INDEX website_trip_user_id ON website_trip USING btree (user_id);


--
-- Name: auth_group_permissions_permission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT auth_group_permissions_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: auth_user_groups_group_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT auth_user_groups_group_id_fkey FOREIGN KEY (group_id) REFERENCES auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: auth_user_user_permissions_permission_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT auth_user_user_permissions_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES auth_permission(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: content_type_id_refs_id_93d2d1f8; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT content_type_id_refs_id_93d2d1f8 FOREIGN KEY (content_type_id) REFERENCES django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: content_type_id_refs_id_d043b34a; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_permission
    ADD CONSTRAINT content_type_id_refs_id_d043b34a FOREIGN KEY (content_type_id) REFERENCES django_content_type(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: group_id_refs_id_f4b32aac; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_group_permissions
    ADD CONSTRAINT group_id_refs_id_f4b32aac FOREIGN KEY (group_id) REFERENCES auth_group(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: user_id_refs_id_40c41112; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_groups
    ADD CONSTRAINT user_id_refs_id_40c41112 FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: user_id_refs_id_4dc23c39; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY auth_user_user_permissions
    ADD CONSTRAINT user_id_refs_id_4dc23c39 FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: user_id_refs_id_c0d12874; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY django_admin_log
    ADD CONSTRAINT user_id_refs_id_c0d12874 FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: website_savedplaces_trip_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY website_savedplaces
    ADD CONSTRAINT website_savedplaces_trip_id_fkey FOREIGN KEY (trip_id) REFERENCES website_trip(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: website_trip_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: project
--

ALTER TABLE ONLY website_trip
    ADD CONSTRAINT website_trip_user_id_fkey FOREIGN KEY (user_id) REFERENCES auth_user(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

