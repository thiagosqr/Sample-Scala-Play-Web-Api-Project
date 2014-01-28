package utils

import play.api.mvc.Filter
import play.api.mvc.RequestHeader
import play.api.mvc.Result

object RestApiFilter extends Filter{
  
  def apply(next: (RequestHeader) => Result)(rh: RequestHeader) = {
	 next(rh)
	 
//     SimpleResult(
//    		header = ResponseHeader(403, Map("Content-Type" -> "text/plain")), 
//    		body = Enumerator("")
//     )

  }
  
//  def apply(next: EssentialAction) = new EssentialAction {
//    def apply(request: RequestHeader) = {
//      if (request.path.startsWith("/admin") && request.session.get("user").isEmpty) {
//    		Iteratee.ignore[Array[Byte]].map(_ => Results.Forbidden())
//      } else {
//        next(request)
//      }
//    }
//  }

}