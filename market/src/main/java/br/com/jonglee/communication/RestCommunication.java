package br.com.jonglee.communication;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jonglee.beans.ProductsData;
import br.com.jonglee.dao.SqlCommands;

@Path("/market")
public class RestCommunication {
	@GET
	@Path("/allProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProductsData> clientes() {
		ArrayList<ProductsData> listData = new ArrayList<ProductsData>();
		ArrayList<ProductsData> listAll = SqlCommands.getInstance().getAllContent();

		// adionando na lista os dados buscados
		for (int i = 0; i < listAll.size(); i++) {
			ProductsData dados = new ProductsData();
			dados.setId(listAll.get(i).getId());
			dados.setTitle(listAll.get(i).getTitle());
			dados.setDescription(listAll.get(i).getDescription());
			dados.setUnit_price(listAll.get(i).getUnit_price());
			listData.add(dados);
		}

		return listData;
	}
	
	@POST
	@Path("/addProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(ProductsData product) {

		if (product.getTitle() == null || product.getTitle().equals("") ||
				product.getDescription() == null || product.getDescription().equals("")) {			
			return Response.status(400).build();
		}

			SqlCommands.getInstance().addInDB(product);
			return Response.status(200).build();

	}
	
	@POST
	@Path("/removeProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProduct(ProductsData product) {

		if (product.getId() == null || product.getId().equals("")) {			
			return Response.status(400).build();
		}

			SqlCommands.getInstance().removeInDB(product);
			return Response.status(200).build();

	}
}
