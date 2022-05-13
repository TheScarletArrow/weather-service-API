using Auth;
using Grpc.Core;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddGrpc();

var app = builder.Build();

app.MapGrpcService<AuthServiceImpl>();

app.Run();

internal sealed class AuthServiceImpl : AuthService.AuthServiceBase
{
	private readonly HashSet<string> _users = new()
	{
		"Sergey",
		"Alexander",
		"Katya",
		"Anton",
		"Marina",
	};

	public override Task<ResponseAuth> Auth(RequestAuth request, ServerCallContext context)
	{
		if (_users.Contains(request.Name))
		{
			return Task.FromResult(new ResponseAuth
			{
				Authenticated = true
			});
		}
		return Task.FromResult(new ResponseAuth
		{
			Authenticated = false
		});
	}
}