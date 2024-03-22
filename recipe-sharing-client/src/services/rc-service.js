
export default class RCService
{
    fetchRequest = async (url, method, body, token) =>
    {
        try
        {
            const response = await fetch(`${process.env.REACT_APP_API_URL}${url}`, {
                method: method,
                body: body,
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    "Authorization": `Bearer ${token}`
                }
            }
            );
            return await response.json();
        } catch (e)
        {
            console.log(e)
        }
    }

    postAuthenticate = async (username, password) =>
    {
        let body = JSON.stringify({
            username: username,
            password: password,
        })
        return await this.fetchRequest('authenticate', 'POST', body);
    };

    SignUp = async (username, password, email, fullName, mobile) =>
    {
        let body = JSON.stringify({
            fullName: fullName,
            username: username,
            email: email,
            password: password,
            mobileNumber: mobile,
        })
        return await this.fetchRequest('signup', 'POST', body);
    };

    GetRecipes = async (token) =>
    {
        try
        {
            const response = await fetch(`${"https://recipe-service-ixhm.onrender.com/api/v1/recipes"}`, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    "Authorization": `Bearer ${token}`
                }
            }
            );
            return await response.json();
        } catch (e)
        {
            console.log(e)
        }
    }

    PostRecipes = async (token, userId = "2323", description = "test", content = "test", author = "test", prepTime = "12", cookTime = "45", categoryId = "3") =>
    {
        try
        {
            const response = await fetch(`${"https://recipe-service-ixhm.onrender.com/api/v1/recipes"}`, {
                method: "POST",
                body: JSON.stringify({
                    userId: userId,
                    description: description,
                    content: content,
                    author: author,
                    prepTime: prepTime,
                    cookTime: cookTime,
                    categoryId: categoryId

                }),
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    "Authorization": `Bearer ${token}`
                }
            }
            );
            return await response.json();
        } catch (e)
        {
            console.log(e)
        }
    };
}
