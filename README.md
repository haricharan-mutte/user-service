# 🧠 First, Understand: What is the Security Context?
---
SecurityContext is a thread-local store in Spring Security that holds the details about the currently authenticated user.

When you set the Authentication object inside SecurityContext, you're telling Spring Security:

"Hey, this user is authenticated, and here's their identity and authorities (roles)."

🔐 Why Set It?
Because Spring uses SecurityContext to:

Check if the user is authenticated.

Evaluate annotations like @PreAuthorize("hasRole('ADMIN')").

Perform access decisions during request processing.

So your manual logic of:

Extracting JWT,

Validating it,

Getting roles,

Creating an Authentication object (e.g. UsernamePasswordAuthenticationToken or JwtAuthenticationToken),

And setting it in SecurityContextHolder.getContext().setAuthentication(auth)
is correct and valid, and it's equivalent to what spring-boot-starter-oauth2-resource-server automates.

---
