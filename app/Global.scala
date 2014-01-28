

import play.api.mvc.WithFilters
import utils.RestApiFilter

object Global extends WithFilters(RestApiFilter){

}