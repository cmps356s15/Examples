package hifzTracker.service;

import com.google.gson.Gson;
import hifzTracker.repository.SurahRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/surahs")
@Stateless
public class SurahService {
    @Inject
    SurahRepository surahRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSurahs() {
        Gson gson = new Gson();
        String surahs = gson.toJson(surahRepository.getSurahs());
        return Response.ok(surahs).build();
    }
}
