<!DOCTYPE html>
    <html>
    <head>
    <meta name="layout"content="main"/>
    <title>Coin Data</title>
    </head>
    <body>

    <div class="container-fluid">
    <div class="alert alert-primary" role="alert">
    <h1>List of Data</h1>
    </div>

    <g:each  status="counter" in="${error}" var="data">
    <div class="alert alert-danger" role="alert">
    The file has error on Line Number ${data}
    </div>
    </g:each>

    <g:link action="file">Upload More</g:link>

    <table class="table">
    <thead>
    <tr>
    <th scope="col">#</th>
    <th scope="col">User ID</th>
    <th scope="col">Number of Coins</th>
    <th scope="col">User Name</th>
    </tr>
    </thead>
    <tbody>
    <g:each status="i" in="${coinDatas}" var="data">
    <tr>
    <th scope="row"> ${i+1} </th>
    <td> ${data.userID} </td>
    <td> ${data.coins} </td>
    <td> ${data.userName} </td>
    </tr>
    </g:each>

    </tbody>
    </table>

    </div>


    </body>
    </html>
