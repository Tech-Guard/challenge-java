package br.com.fiap.techguard.resource;

import br.com.fiap.techguard.dao.ClienteDAO;
import br.com.fiap.techguard.model.Cliente;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.sql.SQLException;
import java.util.List;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private ClienteDAO clienteDAO = new ClienteDAO();

    // POST - Criar cliente
    @POST
    public Response criarCliente(Cliente cliente, @Context UriInfo uriInfo) {
        try {

            boolean usuarioCadastrado = clienteDAO.clienteExiste(cliente.getTelefone(), cliente.getCpf(), cliente.getEmail());

            if (usuarioCadastrado) {
                return Response.status(Response.Status.CONFLICT).entity("Cliente já cadastrado.").build();
            }

            clienteDAO.cadastrar(cliente);

            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.path(cliente.getId()).build()).entity(cliente).build();

        } catch (Exception e) {
            e.printStackTrace();
            // Se ocorrer um erro, retorne uma resposta de erro do servidor
            return Response.serverError().entity("Erro ao criar cliente: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/login")
    public Response loginCliente(@QueryParam("email") String email, @QueryParam("senha") String senha) {
        try {
            Cliente cliente = clienteDAO.buscarPorLogin(email, senha);
            return Response.ok(cliente).build();
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Email não encontrado")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\": \"Email não encontrado\"}")
                        .build();
            } else if (errorMessage.contains("senha incorreta")) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\": \"Senha incorreta\"}")
                        .build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"Erro no servidor\"}")
                    .build();
        }
    }



    // GET - Cliente específico
    @GET
    @Path("/{id}")
    public Response getCliente(@PathParam("id") String id) {
        Cliente cliente = clienteDAO.pesquisarPorId(id);
        if (cliente != null) {
            System.out.println("Cliente:\n" + cliente.toString());
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
    }

    // GET - Listar clientes
    @GET
    public Response getTodosClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        System.out.println("Lista de Clientes:\n" + clientes.toString());
        return Response.ok(clientes).build();

    }

    // PUT - Atualizar cliente
    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") String id, Cliente clienteAtualizado) {
        Cliente cliente = clienteDAO.pesquisarPorId(id);
        if (cliente != null) {

            cliente.setNome(clienteAtualizado.getNome());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setSenha(clienteAtualizado.getSenha());

            clienteDAO.atualizar(cliente);
            System.out.println("Cliente Atualizado:\n" + cliente.toString());
            return Response.ok(cliente).build();
        } else {
            System.out.println("Cliente não encontrado com ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
    }

    // DELETE - Remover cliente
    @DELETE
    @Path("/{id}")
    public Response removerCliente(@PathParam("id") String id) {
        Cliente cliente = clienteDAO.pesquisarPorId(id);
        if (cliente != null) {
            clienteDAO.remover(id);
            System.out.println("Cliente Removido:\n" + cliente.toString());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
    }
}
