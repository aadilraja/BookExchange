import robot from '../assets/Robot.png'

export default function ErrorPage404 ()
{
  return(
    <div className="min-h-screen w-screen bg-white flex flex-col justify-center items-center ">
        <h1 className="text-9xl">Error 404</h1>
        <img src={robot} alt={robot} />

    </div>
  );
}