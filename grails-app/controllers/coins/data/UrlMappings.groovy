package coins.data

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(uri: "/FileUpload/file")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
