package com.physics.api.resource;

import java.io.File;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.physics.api.dao.AnimationsDAO;
import com.physics.api.dao.ImagesDAO;
import com.physics.api.model.Animation;
import com.physics.api.model.Image;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

@Path("content")
public class MultimediaResource {
	
	private Response response;

//	@Path("/base64/{contentId}/{imageId}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Image searchImage(@PathParam("imageId") Long imageId, @PathParam("contentId") Long contentId) {
//		Image image = new ImagesDAO().findImageAsBase64(imageId, contentId);
//		return image;
//	}
//	
	@Path("{contentId}/image/{imageId}")
	@GET
	@Produces("image/*")
	public byte[] getBlobImage(@PathParam("imageId") Long imageId, @PathParam("contentId") Long contentId) {
		byte[] image = new ImagesDAO().findImageAsByte(imageId, contentId);
		return image;
	}
	
	@Path("{contentId}/images")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Image> getAllImagesFromContent(@PathParam("contentId") Long contentId) {
		List<Image> images = new ImagesDAO().findAllImagesFromContent(contentId);
		return images;
	}
	
	@Path("{contentId}/animation/{gifId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public byte[] getBlobGifAnimation(@PathParam("contentId") Long contentId, @PathParam("gifId") Long gifId) {
		byte[] gif = new AnimationsDAO().findAnimationAsByte(gifId, contentId);
		return gif;
	}
	
	@Path("{contentId}/animations")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Animation> getAllAnimationsFromContent(@PathParam("contentId") Long contentId) {
		List<Animation> animations = new AnimationsDAO().listAnimationsOnDatabase();
		return animations;
	}
	
	@GET
	@Path("/{id}")
	@Produces("image/jpg")
	public Response getImage(@PathParam("id") int id) {
		File file = new File("/Users/bruno/Documents/workspace/physics-api/test" + id + ".jpg");
		
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "inline");
		response.header("Content-Type", "image/jpg");
		return response.build();
	}
}