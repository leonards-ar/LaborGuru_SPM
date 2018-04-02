package com.mindpool.spm.report.controller;


/**
 * Created by Anshad Vattapoyil on 10/06/17 2:58 PM.
 */

//@RestController
//@EnableAutoConfiguration
//@RequestMapping("/api/v1/users")
public class UsersController {

//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * Get all users
//     *
//     * @return
//     */
//     @RequestMapping(method = RequestMethod.GET)
//    public ListResponse getAllUsers(HttpServletResponse http) {
//        ListResponse response = new ListResponse();
//        response.setMessage("Successfully Retrieved");
//        response.setStatusCode(http.getStatus());
//        List<User> users = userRepository.findAll();
//        response.setData(users);
//        return response;
//    }
//
//    /**
//     * Create new user
//     *
//     * @param user
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public ListResponse saveUser(@RequestBody final User user, HttpServletResponse http) {
//        userRepository.save(user);
//        ListResponse response = new ListResponse();
//        response.setMessage("Successfully Created");
//        response.setStatusCode(http.getStatus());
//        List<User> users = userRepository.findAll();
//        response.setData(users);
//        return response;
//    }
//
//    /**
//     * Get a specific user
//     *
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ObjectResponse getUser(@PathVariable("id") Integer userId, HttpServletResponse http) {
//        ObjectResponse response = new ObjectResponse();
//        if (userRepository.exists(userId)) {
//            response.setMessage("Successfully Retrieved");
//            response.setStatusCode(http.getStatus());
//            response.setData(userRepository.findOne(userId));
//        } else {
//            response.setMessage("Record not found");
//            response.setStatusCode(404);
//            response.setData(null);
//        }
//        return response;
//    }
//
//    /**
//     * Find and update a user
//     *
//     * @param user
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.PUT)
//    public ObjectResponse updateUser(@RequestBody final User user, HttpServletResponse http) {
//        ObjectResponse response = new ObjectResponse();
//        if (userRepository.exists(user.getId())) {
//            userRepository.updateUser(user.getName(), user.getEmail(), user.getMobile(), user.getId());
//            response.setMessage("Successfully Updated");
//            response.setStatusCode(http.getStatus());
//            response.setData(userRepository.findOne(user.getId()));
//        } else {
//            response.setMessage("Record not found");
//            response.setStatusCode(404);
//            response.setData(null);
//        }
//        return response;
//    }
//
//    /**
//     * Delete a user
//     *
//     * @param userId
//     * @return
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public ListResponse deleteUser(@PathVariable("id") Integer userId, HttpServletResponse http) {
//        ListResponse response = new ListResponse();
//        if(userRepository.exists(userId)) {
//            userRepository.delete(userId);
//            response.setStatusCode(http.getStatus());
//            response.setMessage("Successfully Deleted");
//        } else {
//            response.setStatusCode(404);
//            response.setMessage("Record not found");
//        }
//        List<User> users = userRepository.findAll();
//        response.setData(users);
//        return response;
//    }
}
