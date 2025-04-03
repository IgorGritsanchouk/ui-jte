@import org.springframework.validation.BindingResult
@import java.util.Map
@param com.ui.util.InterMessage messages
@param com.ui.model.CurrentPage currentPage
@param com.ui.model.Employee employee
@param com.ui.model.EmployeeForm employeeForm
@param BindingResult bindingResult

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Information Form</title>
    <link href="main.css" rel="stylesheet" />
</head>

<body class="bg-gray-100 p-6">

<div class="max-w-3xl mx-auto bg-white rounded-xl shadow-md overflow-hidden">

    <form class="p-6" action="/save-employee-vm" method="post">
        <div class="border-b border-gray-900/10 pb-12">
            <h2 class="text-base font-semibold leading-7 text-gray-900">${messages.getMessage("personal.ifo.label")}</h2>
            <p class="mt-1 text-sm leading-6 text-gray-600">${messages.getMessage("personal.address.message")}</p>
            <p class="mt-1 text-sm leading-6 text-gray-500">${messages.getMessage("fields.required.message")}</p>

            @if(currentPage.getMessage() != null)
                <div class="mt-4 p-4 bg-green-100 text-green-700 rounded">
                    ${currentPage.getMessage()}
                </div>
            @endif

            @if(currentPage.getError() != null)
                <div class="mt-4 p-4 bg-red-100 text-red-700 rounded">
                    ${currentPage.getError()}
                </div>
            @endif

            <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">

                <div class="sm:col-span-3">
                    <label for="firstName" class="block text-sm font-medium leading-6 text-gray-900">
                        ${messages.getMessage("first.name.label")} <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="firstName"
                               id="firstName"
                               value="${employee.getFirstName()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                    @if(bindingResult!=null && bindingResult.hasFieldErrors("firstName"))
                        <div style="color: red; font-size: 10px;">
                            ${bindingResult.getFieldError("firstName").getDefaultMessage()}
                        </div>
                    @endif
                </div>

                <div class="sm:col-span-3">
                    <label for="lastName" class="block text-sm font-medium leading-6 text-gray-900">
                        ${messages.getMessage("last.name.label")} <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="lastName"
                               id="lastName"
                               value="${employee.getLastName()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                    @if(bindingResult!=null && bindingResult.hasFieldErrors("lastName"))
                        <div style="color: red; font-size: 10px;">
                            ${bindingResult.getFieldError("lastName").getDefaultMessage()}
                        </div>
                    @endif
                </div>

                <div class="sm:col-span-2">
                    <label for="title" class="block text-sm font-medium leading-6 text-gray-900">Title</label>
                    <div class="mt-2">
                        <select id="title" name="title" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Select title</option>
                            @for (Map.Entry<String, String> entry : employeeForm.getTitles().entrySet())
                                <option value="${entry.getKey()}" selected="${employee.getTitle() != null && employee.getTitle().equals(entry.getKey())}">
                                    ${entry.getValue()}
                                </option>
                            @endfor
                        </select>
                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="birthDate" class="block text-sm font-medium leading-6 text-gray-900">Birth date<span class="text-red-500">*</span></label>
                    <div class="mt-2">
                        <input type="date" placeholder="Select Date" name="birthDate" id="birthDate" value="${employee.getBirthDate().toString()}" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                    @if(bindingResult!=null && bindingResult.hasFieldErrors("birthDate"))
                        <div style="color: red; font-size: 10px;">
                            ${messages.getMessage(bindingResult.getFieldError("birthDate").getDefaultMessage())}
                        </div>
                    @endif
                </div>

                <div class="sm:col-span-2">
                    <label for="hireDate" class="block text-sm font-medium leading-6 text-gray-900">Hire date<span class="text-red-500">*</span></label>
                    <div class="mt-2">
                        <input type="date" placeholder="Select Date" name="hireDate" id="hireDate" value="${employee.convertTimestampToDate(employee.getHireDate()).toString()}" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="col-span-full">
                    <label for="address" class="block text-sm font-medium leading-6 text-gray-900">${messages.getMessage("street.address.label")}</label>
                    <div class="mt-2">
                        <input type="text" name="address" id="streetAddress" value="${employee.getAddress()}" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="country" class="block text-sm font-medium leading-6 text-gray-900">${messages.getMessage("country.label")}<span class="text-red-500">*</span></label>
                    <div class="mt-2">
                        <select id="country" name="country" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Select a country</option>
                            @for (Map.Entry<String, String> entry : employeeForm.getCountries().entrySet())
                                <option value="${entry.getKey()}" selected="${employee.getCountry() != null && employee.getCountry().equals(entry.getKey())}">
                                    ${entry.getValue()}
                                </option>
                            @endfor

                        </select>
                    </div>
                    @if(bindingResult!=null && bindingResult.hasFieldErrors("country"))
                        <div style="color: red; font-size: 10px;">
                            ${bindingResult.getFieldError("country").getDefaultMessage()}
                        </div>
                    @endif
                </div>

                <div class="sm:col-span-2 sm:col-start-1">
                    <label for="city" class="block text-sm font-medium leading-6 text-gray-900">${messages.getMessage("city.label")}</label>
                    <div class="mt-2">
                        <input type="text" name="city" id="city" value="${employee.getCity()}" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="region" class="block text-sm font-medium leading-6 text-gray-900">${messages.getMessage("state.province.label")}</label>
                    <div class="mt-2">
                        <select id="region" name="region" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Select a county</option>
                            @for (Map.Entry<String, String> entry : employeeForm.getRegions().entrySet())
                                <option value="${entry.getKey()}" selected="${employee.getRegion() != null && employee.getRegion().equals(entry.getKey())}">
                                    ${entry.getValue()}
                                </option>
                            @endfor
                        </select>
                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="postalCode" class="block text-sm font-medium leading-6 text-gray-900">${messages.getMessage("postal.code.label")}</label>
                    <div class="mt-2">
                        <input type="text" name="postalCode" id="postalCode" value="${employee.getPostalCode()}" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="managerId" class="block text-sm font-medium leading-6 text-gray-900">Manager<span class="text-red-500">*</span></label>
                    <div class="mt-2">
                        <select id="managerId" name="managerId" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Select manager</option>
                            @for (Map.Entry<Long, String> entry : employeeForm.getEmployeeDropDown().entrySet())
                                <option value="${entry.getKey()}" selected="${employee.getEmployeeId() != null && employee.getEmployeeId().equals(entry.getKey())}">
                                    ${entry.getValue()}
                                </option>
                            @endfor
                        </select>
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="notes" class="block text-sm font-medium leading-6 text-gray-900">Notes</label>
                    <div class="mt-2">
                        <textarea id="notes" name="notes" rows="4" cols="50" class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            ${employee.getNotes()}
                        </textarea>
                    </div>
                </div>


            </div>
        </div>

        <div class="mt-6 flex items-center justify-end gap-x-6">
            <button type="button" class="text-sm font-semibold leading-6 text-gray-900">${messages.getMessage("cancel.label")}</button>
            <button type="submit" class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">${messages.getMessage("save.label")}</button>
        </div>
    </form>

</div>
</body>
</html>