#set($dollar = '$')
<html>
<head>
<title>View Candidate</title>
<link href="css/default.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>Candidate #${dollar}{profile.id}</h2>
Id: ${dollar}{profile.id}
Full name: ${dollar}{profile.firstName} ${dollar}{profile.lastName}
E-Mail: ${dollar}{profile.email}
<br/>
<br/>
Return to <a href="candidates.html">candidates list</a> page.
</body>
</html>